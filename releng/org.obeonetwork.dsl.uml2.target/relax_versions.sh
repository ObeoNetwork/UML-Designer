#!/bin/sh 

sed -i -r -e 's|(<unit id="org.eclipse.nebula.widgets.paperclips.*" version=).*|\1"0.0.0"/>|' ./*.target
sed -i -r -e 's|(<unit id="org.eclipse.sirius.*" version=).*|\1"0.0.0"/>|' ./*.target
sed -i -r -e 's|(<unit id="fr.obeo.*" version=).*|\1"0.0.0"/>|' ./*.target
