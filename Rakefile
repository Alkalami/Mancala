require 'rake/clean'

SRC = FileList["src/*.java"]
CLEAN.include("bin/*.class")

JCLASSES = %w{Board BoardLayout GeomBoard MDialog Mancala MancalaGUI MancalaCLI}

JCLASSES.each do |jclass|
	file "bin/#{jclass}.class" => [ "src/#{jclass}.java"] do
		sh "javac -cp bin/ -d bin/ src/#{jclass}.java"
	end
end

file "bin/MancalaGUI.class" => ["bin/Mancala.class"]
file "bin/MancalaCLI.class" => ["bin/Mancala.class"]

task :main => "bin/MancalaGUI.class"
task :default => :main

task :run => [:main] do
	sh "java -cp bin/ MancalaGUI"
end

