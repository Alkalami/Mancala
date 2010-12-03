require 'rake/clean'
task :default => :makeall

SRC = FileList["src/*.java"]
CLEAN.include("bin/*.class")

JCLASSES = %w{Board BoardLayout GeomLayout MDialog Mancala MancalaGUI MancalaCLI}

JCLASSES.each do |jclass|
	file "bin/#{jclass}.class" => [ "src/#{jclass}.java"] do
		sh "javac -cp bin/ -d bin/ src/#{jclass}.java"
	end
end

task :makeall do
	sh "javac -cp bin/ -d bin/ src/*.java"
end

file "bin/MancalaGUI.class" => ["bin/Mancala.class"]
file "bin/MancalaCLI.class" => ["bin/Mancala.class"]

task :main => "bin/MancalaGUI.class"

task :run => [:main] do
	sh "java -cp bin/ MancalaGUI"
end

