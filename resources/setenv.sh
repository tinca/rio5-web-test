#!/bin/sh
# ====================================================================
# Copyright to the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Sets up the Tomcat startup environment with a CLASSPATH and CATALINA_OPTS
# enabling service discovery using Apache River, and artifact: URL resolution
# using Rio
#
# Created By: Dennis Reedy
# github @ https://github.com/dreedyman
# ====================================================================

# If not set, set the location of Rio
if [ -z "$RIO_HOME" ]; then
    RIO_HOME=/Users/dreedy/dev/src/rioproj/rio/current/distribution/target/rio-5.1.4
fi

echo "Using RIO_HOME:        $RIO_HOME"
	
# Find a file in RIO_HOME
ff() {
	echo `find $RIO_HOME/$1 -name "$2*"`
}

# Set the CLASSPATH to include Rio and River jars. This is required for discovery
# as well as for using the artifact: URL
CLASSPATH=`ff lib jsk-platform`
CLASSPATH=$CLASSPATH:`ff lib rio-platform`
CLASSPATH=$CLASSPATH:`ff lib groovy-all`
CLASSPATH=$CLASSPATH:`ff lib/logging slf4j-api`
CLASSPATH=$CLASSPATH:`ff lib/logging slf4j-jdk14`

# Set CATALINA_OPTS to include location for rio home
CATALINA_OPTS="$CATALINA_OPTS -Drio.home=$RIO_HOME"

echo "Using CATALINA_OPTS:  $CATALINA_OPTS"