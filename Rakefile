require 'rake'
require 'rake/clean'

SRC = FileList["*.java"]
CLEAN.include("bin/*.class")

rule '.class' => '.java' do |t|
	sh "javac -cp .classpath src/#{t.source} -d bin/"
end

file "bin/Mancala.class" => ["src/Mancala.java"] do
	sh "javac -cp bin/ src/Mancala.java -d bin/"
end

file "bin/MancalaCLI.class" => ["src/MancalaCLI.java"] do
	sh "javac -cp bin/ src/MancalaCLI.java -d bin/"
end

file "bin/MancalaGUI.class" => ["src/MancalaGUI.java"] do
	sh "javac -cp bin/ src/MancalaGUI.java -d bin/"
end

file "bin/MancalaCLI.class" => ["bin/Mancala.class"]
file "bin/MancalaGUI.class" => ["bin/Mancala.class"]
