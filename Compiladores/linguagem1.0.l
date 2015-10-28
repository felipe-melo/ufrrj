%{
#include <stdio.h>
#include <stdlib.h>
#include <string>
%}

ESPACO 		[ \t]
DIGITO 		[0-9]
LETRA 		[A-Za-z_]

INTEGER_VAR int
STRING_VAR 	string
FLOAT_VAR 	float
DOUBLE_VAR 	double
BOOLEAN_VAR boolean

FOR 		for
WHILE 		while
IF 			if
ELSE 		else
ELSE_IF 	elif
BREAK 		break
CONTINUE 	continue
SWITCH 		switch
CASE 		case

NEW_LINE 	[\n]

EQUAL		=
GREATER		>
SMALLER		<
GREATER_E	>=
SMALLER_E	<=
DIFERENTE	<>

COLC_OPEN	[
COLC_CLOSE	]

AND			and
NOT			¬
OR			or

NEG			-

TRUE		true
FALSE		false

DELIM 		{ESPACO}+
NUM 		{DIGITO}+
REAL 		{NUM}+("."{NUM}+)?
CHAR 		\'({LETRA}|{NUM})\'
INTEGER 	[+-]?{DIGITO}|[1-9]{DIGITO}*
BOOL 		({TRUE}|{FALSE})

ID 			{LETRA}({LETRA}|{NUM})*

STRING 		\"([^\"\n]|\\\")*\"
CONCAT 		&

MAIN 		main

TIPOS 		{INTEGER_VAR}|{STRING_VAR}|{DOUBLE_VAR}|{BOOLEAN_VAR}

R_OP 		{EQUAL}|{GREATER}|{SMALLER}|{GREATER_E}|{SMALLER_E}|{DIFERENTE}
L_OP 		{AND}|{OR}

OPS 		{SUM}|{MIN}|{MUL}|{DIV}|{MOD}

LOOPS 		{FOR}|{WHILE}

%%

{NEW_LINE}			{line++;}
{DELIM} 			{}

"main"				{return TK_MAIN;}
"read"				{return TK_READ;}
"write"				{return TK_WRITE;}
"<-"				{return TK_ASSIGN;}
"^"					{return TK_POW;}
"func"				{return TK_FUNC;}
"return"			{return TK_RETURN;}

{TIPOS}				{
					if (((string)yytext) == "string"){
						yylval.type = "char*";
					}else if (((string)yytext) == "boolean"){
						yylval.type = "bool";
					}else{ 
						yylval.type = yytext;
					}
						return TK_TIPO;
					}

[();,:|]				{return *yytext;}

[{]					{yylval.translate = yytext;
					return TK_OPEN;}

[}]					{yylval.translate = yytext;
					return TK_CLOSE;}

[\[]				{
					yylval.translate = yytext;
					return TK_COLCH_OPEN;}

[\]]				{yylval.translate = yytext;
					return TK_COLCH_CLOSE;}

{R_OP}				{
					if (((string)yytext) == "="){
						yylval.translate = "==";
					}else if (((string)yytext) == "<>"){
						yylval.translate = "!=";
					}else{
						yylval.translate = yytext;
					}
					return TK_REL_OP;}

{L_OP}				{if (((string)yytext) == "and"){
						yylval.translate = "&&";
					}else if (((string)yytext) == "or"){
						yylval.translate = "||";
					}
					return TK_LOG_OP;}

"++"				{yylval.translate = yytext;
					return TK_PP;
					}

"--"				{yylval.translate = yytext;
					return TK_MM;
					}

[&+-]				{yylval.translate = yytext;
					return TK_OP1;
					}
[*/\%]				{yylval.translate = yytext;
					return TK_OP2;
					}

{INTEGER}           {
					yylval.translate = yytext; 
					yylval.type = "int";
					return TK_NUM;}

{NOT}	            {yylval.translate = yytext; 
					yylval.type = "!";
					return TK_NOT;}

{REAL}	            {yylval.translate = yytext;
					yylval.type = "double"; 
					return TK_NUM;}

{STRING}            {yylval.translate = yytext;
					yylval.type = "char*";
					return TK_STRING;}

{CHAR}	            {yylval.translate = yytext[1];
					yylval.type = "char";
					return TK_CHAR;}

{BOOL}	            {yylval.translate = yytext;
					yylval.type = "bool";
					return TK_BOOL;}

{SWITCH}			{yylval.translate = yytext;
					return TK_SWITCH;}

{CASE}				{yylval.translate = yytext;
					return TK_CASE;}

{FOR}				{yylval.translate = yytext;
					return TK_FOR;}

{WHILE}				{yylval.translate = yytext;
					return TK_WHILE;}

{ELSE_IF}			{yylval.translate = yytext;
					return TK_ELSE_IF;}

{IF}				{yylval.translate = yytext;
					return TK_IF;}

{ELSE}				{yylval.translate = yytext;
					return TK_ELSE;}

{BREAK}				{yylval.translate = yytext;
					return TK_BREAK;}

{CONTINUE}			{yylval.translate = yytext;
					return TK_CONTINUE;}

{ID}                {yylval.translate = yytext;
					return TK_ID;}

<<EOF>>   	     	{return 0;}

.   	         	{*yytext;}

%%
