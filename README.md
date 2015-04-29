inviti project
======
Prerequisites :
What is required for successfull build : JDK 7+, Gradle
What is required for running : Neo4J standalone, Cassandra Standalone
Installation Instructin for Neo4j : follow Neo4j official instructions.
Installatoin instruciton for Cassandra:
Example is based on MacOS X (suitable for any *nix environments)
Install Cassandra
mkdir -p ~/opt/packages && cd $_

curl -O http://psg.mtu.edu/pub/apache/cassandra/2.1.2/apache-cassandra-2.1.2-bin.tar.gz  (please choose the latest version)

gzip -dc apache-cassandra-2.1.2-bin.tar.gz | tar xf -

ln -s ~/opt/packages/apache-cassandra-2.1.2 ~/opt/cassandra
Leave configuration variable state in conf/cassandra.yaml as is.

Add Cassandra to your PATH
Update your PATH to include Cassandra.

open -a TextEdit ~/.profile
# include locally installed Cassandra in PATH
if [ -d "$HOME/opt" ]; then
    PATH="$PATH:$HOME/opt/cassandra/bin"
fi

Start the Cassandra server
cassandra -f
