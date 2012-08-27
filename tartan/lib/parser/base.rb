module Parser
  module Base
    def count_indent(line)
      l = line.gsub(/\t/, " " * TAB_SIZE)
      count = 0
      count += 1 while line[count] == " "
      count
    end

    def print_ast(decl)
      puts decl
      decl.children.each{|c| print_ast c}
    end
  end
end
