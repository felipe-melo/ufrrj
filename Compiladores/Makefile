all:

	flex linguagem1.0.l
	yacc -d sintatica.y
	g++ y.tab.c -lfl
	./a.out < exemplo4 > teste.cpp

	g++ teste.cpp -o teste

	./teste
