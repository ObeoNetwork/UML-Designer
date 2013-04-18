/***************************************************************************************
        Nested list collapsing script written by Mark Wilton-Jones - 21/11/2003
Version 2.2.0 - this script takes existing HTML nested UL or OL lists, and collapses them
            Updated 13/02/2004 to allow links in root of expanding branch
                  Updated 09/09/2004 to allow state to be saved
          Updated 07/10/2004 to allow page address links to be highlighted
Updated 28/11/2004 to allow you to force expand/collapse links to use just the extraHTML
****************************************************************************************

Please see http://www.howtocreate.co.uk/jslibs/ for details and a demo of this script
Please see http://www.howtocreate.co.uk/jslibs/termsOfUse.html for terms of use
_________________________________________________________________________

You can put as many lists on the page as you like, each list may have a different format.

To use:
_________________________________________________________________________

Inbetween the <head> tags, put:

	<script src="PATH TO SCRIPT/listCollapse.js" type="text/javascript" language="javascript1.2"></script>
_________________________________________________________________________

Define the HTML. Note that to correctly nest lists, child OLs or ULs should be children of an LI element,
not direct descendents of their parent OL/UL. The text used to expand the branch should be written
between the <li> tag and the <UL/OL/A> tag, and should only contain HTML that is permitted inside an 'A'
element. Note; Opera 7 will lose any style attributes you define in this text - use classes instead.

<ul id="someID">
	<li>Book 1
		<ul>
			<li><a href="someHref">Chapter 1</a></li>
			<li><a href="someHref">Chapter 2</a></li>
		</ul>
	</li>
	<li><a href="elsewhere.html">Book 2</a>
		<ul>
			<li><a href="someHref">Chapter 1</a></li>
			<li><a href="someHref">Chapter 2</a></li>
		</ul>
	</li>
	<li>Book 3
		<ul>
			<li><a href="someHref">Chapter 1</a></li>
			<li>Cha<span class="doMore">pt</span>er 2
				<ul>
					<li><a href="someHref">Sub 1</a></li>
					<li><a href="someHref">Sub 2</a></li>
				</ul>
			</li>
		</ul>
	</li>
</ul>
________________________________________________________________________
Now you need to trigger the collapsing, using <body onload, window.onload or by putting the collapse
commands in a script just before the </body> tag. If using either onload technique, you must not use
any other scripts that rely on the onload event.

compactMenu(theRootID,shouldAutoCollapse,extraHTML[,useMinimalLink]);
  oID = string: ID of root nest element, must be a UL or OL; this will not be collapsed, but any child
  UL/OLs will be (note, if the root nest element is a UL, all child lists should be ULs - the same is
  true for OLs; if the root nest element is OL, all child lists should be OLs)
  shouldAutoCollapse = bool: auto-collapse unused branches
  extraHTML = string: HTML to insert to collapsible branches - usually '&plusmn; '
  useMinimalLink = bool: normally the expand/collapse link will use both extraHTML and the original list
  item text - if the list item text is already a link, this will not be included - set this option to
  true to force the script to use only the extraHTML as the link, even if the rest of the list item is
  not a link - this option will only be respected if you also provide some extraHTML

eg 1.
<body onload="compactMenu('someID',true,'&plusmn; ');">

eg 2.
<script type="text/javascript" language="javascript1.2"><!--
window.onload = function () { compactMenu('someID',false,'&plusmn; '); }
//--></script>

eg 3.
<script type="text/javascript" language="javascript1.2"><!--
compactMenu('someID',true,'&plusmn; ');
//--></script>
</body>

stateToFromStr(theRootID);
  oID = string: ID of root nest element, must be a UL or OL; returns a string representing all expanding
  branches - can be used with my cookie script to save state when unloading the page
stateToFromStr(theRootID,stringRepresentation);
  oID = string: ID of root nest element, must be a UL or OL;
  stringRepresentation = string: string representation of expanded branches, as created above
  must be called _after_ collapsing the list - values can be recovered from cookies using my cookie script
note: this facility will not be able to take changes in the list structure into account - use session cookies
or short-term cookies to avoid longer term structure change problems

selfLink(theRootID,newClass,shouldExpandBranch);
  theRootID = string: ID of root nest element, must be a UL or OL;
  newClass = string: new class name to add to any existing class names
  shouldExpandBranch = bool: expand branches to show the first matching link
  allows you to highlight links to the current page that appear in the list
  must be called _after_ collapsing the list
  address hash and port are not included in the comparison - links containing href="#" are always ignored

My cookie script is available on http://www.howtocreate.co.uk/jslibs/
	<body onload="compactMenu('someID',true,'&plusmn; ');stateToFromStr(theRootID,retrieveCookie('menuState'));"
	onunload="setCookie('menuState',stateToFromStr(theRootID),31536000);">
____________________________________________________________________________________________________*/
		var openLists = [], oIcount = 0;
		//*******************************************************************************
		// Nisad Sivcevic - 20050107
		var oPl = '<img src="./plus.gif" align="baseline" border="0" hspace="1" height="15" width="12">'
		var oPlImg = 'plus.gif';
		var oMnImg = 'minus.gif';
		//*******************************************************************************
		function compactMenu(oID,oAutoCol,oPlMn,oMinimalLink) {
			if( !document.getElementsByTagName || !document.childNodes || !document.createElement ) { return; }
			var baseElement = document.getElementById( oID ); if( !baseElement ) { return; }
			compactChildren( baseElement, 0, oID, oAutoCol, oPlMn, baseElement.tagName.toUpperCase(), oMinimalLink && oPlMn );
		}
		function compactChildren( oOb, oLev, oBsID, oCol, oPM, oT, oML ) {
			if( !oLev ) { oBsID = escape(oBsID); if( oCol ) { openLists[oBsID] = []; } }
			for( var x = 0, y = oOb.childNodes; x < y.length; x++ ) { if( y[x].tagName ) {
				//for each immediate LI child
				var theNextUL = y[x].getElementsByTagName( oT )[0];
				if( theNextUL ) {
					//collapse the first UL/OL child
					theNextUL.style.display = 'none';
					//create a link for expanding/collapsing
					var newLink = document.createElement('A');
					newLink.setAttribute( 'href', '#' );
					newLink.onclick = new Function( 'clickSmack(this,' + oLev + ',\'' + oBsID + '\',' + oCol + ',\'' + escape(oT) + '\');return false;' );
					//wrap everything upto the child U/OL in the link
					if( oML ) { var theHTML = 'WWW'; } else {
						var theT = y[x].innerHTML.toUpperCase().indexOf('<'+oT);
						var theA = y[x].innerHTML.toUpperCase().indexOf('<A');
						var theHTML = y[x].innerHTML.substr(0, ( theA + 1 && theA < theT ) ? theA : theT );
						while( !y[x].childNodes[0].tagName || ( y[x].childNodes[0].tagName.toUpperCase() != oT && y[x].childNodes[0].tagName.toUpperCase() != 'A' ) ) {
							y[x].removeChild( y[x].childNodes[0] ); }
					}
					y[x].insertBefore(newLink,y[x].childNodes[0]);
					y[x].childNodes[0].innerHTML = oPM + theHTML.replace(/^\s*|\s*$/g,'');
					theNextUL.MWJuniqueID = oIcount++;
					compactChildren( theNextUL, oLev + 1, oBsID, oCol, oPM, oT, oML );
		} } } }
		function clickSmack( oThisOb, oLevel, oBsID, oCol, oT ) {
			if( oThisOb.blur ) { oThisOb.blur(); }
			oThisOb = oThisOb.parentNode.getElementsByTagName( unescape(oT) )[0];
			if( oCol ) {
				for( var x = openLists[oBsID].length - 1; x >= oLevel; x-=1 ) { if( openLists[oBsID][x] ) {
					openLists[oBsID][x].style.display = 'none'; 
					//*******************************************************************************
					// Nisad Sivcevic - 20050107
					openLists[oBsID][x].previousSibling.innerHTML = openLists[oBsID][x].previousSibling.innerHTML.replace(oMnImg,oPlImg); 
					//*******************************************************************************
					if( oLevel != x ) { openLists[oBsID][x] = null; }
				} }
				if( oThisOb == openLists[oBsID][oLevel] ) { openLists[oBsID][oLevel] = null; }
				else {	oThisOb.style.display = 'block'; 
						//*******************************************************************************
						// Nisad Sivcevic - 20050107
						oThisOb.previousSibling.innerHTML = oThisOb.previousSibling.innerHTML.replace(oPlImg,oMnImg); 
						//*******************************************************************************
						openLists[oBsID][oLevel] = oThisOb; }
			} else { oThisOb.style.display = ( oThisOb.style.display == 'block' ) ? 'none' : 'block'; }
		}
		function stateToFromStr(oID,oFStr) {
			if( !document.getElementsByTagName || !document.childNodes || !document.createElement ) { return ''; }
			var baseElement = document.getElementById( oID ); if( !baseElement ) { return ''; }
			if( !oFStr && typeof(oFStr) != 'undefined' ) { return ''; } if( oFStr ) { oFStr = oFStr.split(':'); }
			for( var oStr = '', l = baseElement.getElementsByTagName(baseElement.tagName), x = 0; l[x]; x++ ) {
				if( oFStr && MWJisInTheArray( l[x].MWJuniqueID, oFStr ) && l[x].style.display == 'none' ) { l[x].parentNode.getElementsByTagName('a')[0].onclick(); }
				else if( l[x].style.display != 'none' ) { oStr += (oStr?':':'') + l[x].MWJuniqueID; }
			}
			return oStr;
		}
		function MWJisInTheArray(oNeed,oHay) { for( var i = 0; i < oHay.length; i++ ) { if( oNeed == oHay[i] ) { return true; } } return false; }
		function selfLink(oRootElement,oClass,oExpand) {
			if(!document.getElementsByTagName||!document.childNodes) { return; }
			oRootElement = document.getElementById(oRootElement);
			for( var x = 0, y = oRootElement.getElementsByTagName('a'); y[x]; x++ ) {
				if( y[x].getAttribute('href') && !y[x].href.match(/#$/) && getRealAddress(y[x]) == getRealAddress(location) ) {
					y[x].className = (y[x].className?(y[x].className+' '):'') + oClass;
					if( oExpand ) {
						oExpand = false;
						for( var oEl = y[x].parentNode, ulStr = ''; oEl != oRootElement && oEl != document.body; oEl = oEl.parentNode ) {
							if( oEl.tagName && oEl.tagName == oRootElement.tagName ) { ulStr = oEl.MWJuniqueID + (ulStr?(':'+ulStr):''); } }
						stateToFromStr(oRootElement.id,ulStr);
		} } } }
		function getRealAddress(oOb) { return oOb.protocol + ( ( oOb.protocol.indexOf( ':' ) + 1 ) ? '' : ':' ) + oOb.hostname + ( ( typeof(oOb.pathname) == typeof(' ') && oOb.pathname.indexOf('/') != 0 ) ? '/' : '' ) + oOb.pathname + oOb.search; }
