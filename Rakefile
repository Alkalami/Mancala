require 'raven'
require 'rake/clean'

SRC = FileList["src/*.java"]
CLEAN.include("bin/*.class")

javac 'compile' do |t|
	t.build_path << 'src/'
end

