%{
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string>
#include <sstream>
#include <utility>
#include <vector>
#include <map>

#define YYSTYPE atributos

#define MSG_SAUL "better call Saul"

#define ERROR_DECLA "Variável já declarada"
#define RETORNO_INC "Retorno incompatível"
#define VAR_NOT_DECLA "Variável não declarada"
#define FUN_NOT_DECLA "Função não declarada"
#define ATTR_INVAL "Atribuição inválida"
#define INVAL_OP "Operação com tipos incompatíveis"
#define INC_TYPE "Tipo incompatível"
#define INV_BREAK "Break inválido"
#define DIM_ERRO "As dimensões de um arry devem ser números inteiros"
#define DIM_ERRO_NO_VAL "valor indefinido para dimensão de array"

#define INC_VET_SIZE "Dimensões incompatíveis para atribuição de vetor"

#define TIPO_INCON "Tipo incompatível com a operação"

#define COMANDO_INC "Comando incompatível com escopo"

#define INC_PARAM "Parametros de tipo incompatível"

#define DEF_INT "0"
#define DEF_DOU "0.0"
#define DEF_STR ""
#define DEF_BOO false
#define DEF_INT "0"

using namespace std;

struct atributos {
	string type;
	string translate;
	string auxOp;

	string init;
	string middle;
	string end;
	bool isArray;
	vector<string> dimensions;
	vector<pair<string, string> > params;
};

struct Node {
	string realVar;
	string type;
	string value;
	string alias;
	bool isVet;
	bool isParam;
	int size;
	vector<string> dimensions;
};

struct FuncNode {
	string realName;
	string type;
	string alias;
	bool isVet;
	vector<pair<string, string> > params;
};

struct NodeContext {
	string labelInit;
	string labelMiddle;
	string labelEnd;
	bool isLoop;
	vector<Node> tabela;
	vector<FuncNode> funcoes;
};

struct Conversions {
	string op1;
	string op2;
	string op;
	string type;
	string cType;
};

int i, j, currentContext;

int line;

vector<Conversions> convertions;
vector<NodeContext> contexts;

string funcType;

int vetFlag = 1;
vector<string> vetTypes;

/*FUNÇÔES*/
int yylex(void);

void yyerror(string);

void setConversions();

string printAllVariable(bool clear);

bool getContextLabelEnd(string &label);

bool getContextLabelInit(string &label);

bool getContextVariable(atributos &at, Node &node);

string variableGenerate();
void insertContext(bool);

string getLastTemp();

string getLastFuncTemp();

void insertTable(string, string, int, bool, bool);
void insertFuncTable(string, string, bool, vector<pair<string, string> >);

bool isAlreadyDeclaredInContext(string);

void yyerror(string);

bool canOperate(atributos &at1, atributos &at2, string, string &type);
bool canOperateAssign(atributos &at1, atributos &at2);

bool getContextSizeVariable(string, int &size);
void contatString(atributos &at, atributos at1, atributos at2, string variable);
bool getContextLabelMiddle(string &label);
bool isIntVariable(string);
void updateVarSize(string, int);
vector<string> *getVariableDim(string);
void setDimensions(string, vector<string>);
string getContextStringVariable(string name);

string CaseLabelGenerate();

bool getContextFunction(atributos &at, FuncNode &node);

string getVariableType(string);
/*FUNÇÔES*/


%}

/*comandos*/
%token TK_IF TK_FOR TK_WHILE TK_ELSE TK_ELSE_IF TK_CONTINUE TK_BREAK TK_MAIN TK_READ TK_WRITE
TK_SWITCH TK_CASE TK_FUNC TK_RETURN TK_VOID
/*comandos*/

/*tipos*/
%token TK_TIPO TK_NUM TK_BOOL TK_STRING TK_INT TK_CHAR
/*tipos*/

/*operadores*/
%token TK_PP TK_MM TK_OP1 TK_OP2 TK_POW TK_REL_OP TK_NOT TK_LOG_OP
/*oepradores*/

%token TK_ID  TKASSING

%token TK_OPEN TK_COLCH_OPEN TK_COLCH_CLOSE TK_CLOSE

%start S

%left TK_LOG_OP

%left TK_REL_OP

%right TK_NOT

%left TK_OP1

%left TK_OP2

%nonassoc TK_ASSIGN

%%

S 	 			: FUNC DECLARACOES ';' MAIN '(' ')' COMANDO {
					cout << "\n\n/*Compilador BlueSky*/\n" << 
					"#include <iostream>\n#include <string.h>\n#include <stdio.h>\n#include <stdlib.h>\nusing namespace std;\n"  << 
					$1.translate << "\n\nint main(void) {\n" << $7.auxOp << $2.auxOp << $2.translate << "\n" << $7.translate << "\n\treturn 0;\n}" << endl; 
				}
				| FUNC MAIN '(' ')' COMANDO {
					cout << "\n\n/*Compilador BlueSky*/\n" << 
					"#include <iostream>\n#include <string.h>\n#include <stdio.h>\n#include <stdlib.h>\nusing namespace std;\n"  <<
					$1.translate << "\n\nint main(void) {\n" << $5.auxOp << "\n" << $5.translate << "\n\treturn 0;\n}" << endl; 
				};

MAIN 			: TK_MAIN {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};

BLOCO			: OPEN COMANDOS CLOSE {
					$$.translate = $2.translate;
					$$.auxOp = printAllVariable(false);
				};

BLOCO_FUNC		: OPEN COMANDOS CLOSE {
					$$.translate = $2.translate;
					$$.auxOp = printAllVariable(true);
				};

IF 				: TK_IF {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};

FUNC_ 			: {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};

ELSES 			: ELSE_P {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};
				| ELSE_IF;

ELIF 			: TK_ELSE_IF {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};

ELSE_P 			: TK_ELSE COMANDO CLOSE_BLOCK {
					$$.translate = $2.translate;
				};

ELSE_IF 		: ELIF '(' E ')' COMANDO CLOSE_BLOCK {
					if ($3.type == "bool"){
						string end = $1.end;
						$$.translate = $3.auxOp;
						$$.translate += "\n\tif (!(" + $3.translate + ")) goto " + end + ";";
						$$.translate += $5.translate;
						$$.translate += "\n" + end + ":";
					}else{
						yyerror(INC_TYPE);
					}
				}
				| ELIF '(' E ')' COMANDO ELSES CLOSE_BLOCK {
					if ($3.type == "bool"){
						string middle = $1.init;
						string end = $1.end;
						$$.translate = $3.auxOp;
						$$.translate += "\n\tif (!(" + $3.translate + ")) goto " + middle + ";";
						$$.translate += $5.translate;
						$$.translate += "\n\tgoto " + end + ";";
						$$.translate += "\n" + middle + ":";
						$$.translate += $6.translate;
						$$.translate += "\n" + end + ":";
					}else{
						yyerror(INC_TYPE);
					}
				};

FOR				: TK_FOR {
					insertContext(true);
					$$.init = contexts[currentContext].labelInit;
					$$.middle 	= contexts[currentContext].labelMiddle;
					$$.end 	= contexts[currentContext].labelEnd;
				};

SWITCH			: TK_SWITCH {
					insertContext(true);
					$$.init = contexts[currentContext].labelInit;
					$$.end 	= contexts[currentContext].labelEnd;
				};

WHILE			: TK_WHILE {
					insertContext(true);
					$$.init = contexts[currentContext].labelInit;
					$$.middle = contexts[currentContext].labelEnd;
				};

OPEN 			: TK_OPEN {
					insertContext(false);
					$$.init = contexts[currentContext].labelInit;
					$$.end = contexts[currentContext].labelEnd;
				};

CLOSE 			: TK_CLOSE {
					currentContext--;
				};

CLOSE_BLOCK		: {
					currentContext--;
				};

COMANDOS		: COMANDO COMANDOS {
					$$.translate = $1.translate + $2.translate;
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
				};

COMANDO 		: COMAND {
					string init, end;
					getContextLabelInit(init);
					getContextLabelEnd(end);
					$$.init = init;
					$$.end = end;
					$$.translate = $1.translate;
					$$.auxOp = $1.auxOp;
				};

RETURN 			: TK_RETURN E ';' {
					atributos at1;
					at1.type = funcType;
					if (canOperateAssign(at1, $2)){
						$$.auxOp = $2.auxOp;
						$$.translate = "\n\treturn " + $2.translate + ";";
						$$.type = $2.type;
					}else{
						yyerror(RETORNO_INC); //retorno incompatível
					}
				}
				| TK_RETURN ';' {
					if (funcType == "void"){
						$$.auxOp = "";
						$$.translate = "\n\treturn;";
						$$.type = "void";
					}else{
						yyerror(RETORNO_INC); //retorno incompatível
					}
				};

FUNC_TYPE		: TK_FUNC FUNC_ TK_TIPO TK_ID '(' PARAMS ')' {
					funcType = $3.type;
					insertFuncTable($4.translate, $3.type, false, $6.params);
					string aux = "";
					for (int i = 0; i < $6.params.size()-1; i++){
						aux += $6.params[i].first + " " + $6.params[i].second + ", ";
					}
					aux += $6.params[$6.params.size()-1].first + " " + $6.params[$6.params.size()-1].second;
					$$.translate = "\n" + $3.type + " " + getLastFuncTemp() + "(" + aux + ") {";
				}
				| TK_FUNC FUNC_ TK_ID '(' PARAMS ')' {
					funcType = "void";
					insertFuncTable($3.translate, "void", false, $5.params);
					string aux = "";
					for (int i = 0; i < $5.params.size()-1; i++){
						aux += $5.params[i].first + " " + $5.params[i].second + ", ";
					}
					aux += $5.params[$5.params.size()-1].first + " " + $5.params[$5.params.size()-1].second;
					$$.translate = "\nvoid " + getLastFuncTemp() + "(" + aux + ") {";
				};

FUNC			: FUNC_TYPE BLOCO_FUNC FUNC {
					$$.translate += $2.auxOp + $2.translate + "\n}" + $3.translate;
				}
				| {
					$$.translate = "";
				};

PARAMS			: TIPO TK_ID PARAM {
					if (!isAlreadyDeclaredInContext($2.translate)){
						insertTable($2.translate, $1.type, 0, false, true);
						$$.params = $3.params;
						pair<string, string> p ($1.type, getLastTemp());
						$$.params.insert($$.params.begin(), p);
					}else{
						yyerror(ERROR_DECLA);
					}
				}
				| {
					$$.translate = "";
					$$.params.clear();
				};

PARAM			: ',' TIPO TK_ID PARAM {
					if (!isAlreadyDeclaredInContext($2.translate)){
						insertTable($3.translate, $2.type, 0, false, true);
						$$.params = $4.params;
						pair<string, string> p ($2.type, getLastTemp());
						$$.params.insert($$.params.begin(), p);
					}else{
						yyerror(ERROR_DECLA);
					}
				}
				| {
					$$.translate = "";
					$$.params.clear();
				};

PARAMS_CALL		: E PARAM_CALL {
					if ($2.isArray){
						$$.params = $2.params;
						pair<string, string> p ($1.type, $1.translate);
						$$.params.insert($$.params.begin(), p);
					}else {
						$$.params = $2.params;
						pair<string, string> p ($1.type, $1.translate);
						$$.params = $2.params;
						$$.params.insert($$.params.begin(), p);
					}
					$$.auxOp = $1.auxOp + $2.auxOp;
				}
				| {
					$$.translate = "";
					$$.params.clear();
					$$.auxOp = "";
				};

PARAM_CALL		: ',' E PARAM_CALL {
					if ($2.isArray){
						$$.params = $3.params;
						pair<string, string> p ($2.type, $2.translate);
						$$.params.insert($$.params.begin(), p);
					}else {
						$$.params = $3.params;
						pair<string, string> p ($2.type, $2.translate);
						$$.params.insert($$.params.begin(), p);
					}
					$$.auxOp = $2.auxOp + $3.auxOp;
				}
				| {
					$$.translate = "";
					$$.params.clear();
					$$.auxOp = "";
				};

FUNC_CALL		: TK_ID '(' PARAMS_CALL ')' {
					$$.translate = $1.translate;
					$$.params = $3.params;
					$$.auxOp = $3.auxOp;
				};

COMAND 			: E ';' {
					$$.translate = $1.auxOp;
					$$.translate += "\n\t" + $1.translate + ";";
				}
				| DECLARACOES ';' {
					$$.translate = $1.auxOp + $1.translate;
				}
				| RETURN {
					$$.translate = $1.auxOp + $1.translate;
				}
				| ATRIBUICAO ';' {
					$$.translate = $1.auxOp;
					$$.translate += $1.translate;
				}
				| BLOCO {
					$$.translate = $1.translate;
				}
				| IF '(' E ')' COMANDO CLOSE_BLOCK COMANDOS {
					if ($3.type == "bool"){
						string end = $1.end;
						$$.translate = $3.auxOp;
						$$.translate += "\n\tif (!(" + $3.translate + ")) goto " + end + ";";
						$$.translate += $5.translate;
						$$.translate += "\n" + end + ":";
						$$.translate += $7.translate;
					}else{
						yyerror(INC_TYPE);
					}
				}
				| IF '(' E ')' COMANDO CLOSE_BLOCK ELSES COMANDOS {
					if ($3.type == "bool"){
						string middle = $1.init;
						string end = $1.end;
						$$.translate = $3.auxOp;
						$$.translate += "\n\tif (!(" + $3.translate + ")) goto " + middle + ";";
						$$.translate += $5.translate;
						$$.translate += "\n\tgoto " + end + ";";
						$$.translate += "\n" + middle + ":";
						$$.translate += $7.translate;
						$$.translate += "\n" + end + ":";
						$$.translate += $8.translate;
					}else{
						yyerror(INC_TYPE);
					}
				}
				| SWITCH '(' E ')' OPEN CASES CLOSE CLOSE_BLOCK {
					$$.translate = $3.auxOp + $6.auxOp;
					vector<string> labels;
					for (int i = 0; i < $6.params.size(); i++) {
						labels.push_back(CaseLabelGenerate());
						atributos *at1 = new atributos();
						at1->type = $6.dimensions[i];
						at1->translate = $6.params[i].first;
						if (canOperateAssign($3, *at1)){
							$$.translate += $3.auxOp + at1->auxOp;
							//params += at1->translate + ",";
						}
						$$.translate += "\n\tif (" + $3.translate + " == " + at1->translate + ") goto " + labels.back() + ";";
					}

					for (int i = 0; i < $6.params.size(); i++) {
						$$.translate += "\n" + labels[i] + ":" + $6.params[i].second;
					}
					$$.translate += "\n" + $1.end + ":";
				}
				| FOR '(' COMANDO E ';' COMANDO ')' COMANDO CLOSE_BLOCK COMANDOS {  //resolver o continue
					if ($4.type == "bool") {
						string init = $1.init;
						string end  = $1.end;
						string middle  = $1.middle;
						$$.translate = $3.auxOp + $3.translate;
						$$.translate += "\n" + init + ":";
						$$.translate += $4.auxOp;
						$$.translate += "\n\tif (!(" + $4.translate + ")) goto " + end + ";";
						$$.translate += $8.translate;
						$$.translate += "\n" + middle + ": ";
						$$.translate += $6.translate;
						$$.translate += "\n\tgoto " + init + ";";
						$$.translate += "\n" + end + ":";
						$$.translate += $10.translate;
					}else{
						yyerror(INC_TYPE);
					}
				}
				| WHILE '(' E ')' COMANDO CLOSE_BLOCK COMANDOS {
					if ($3.type == "bool"){
						string init = $1.init;
						string end  = $1.middle;
						$$.translate = $3.auxOp;
						$$.translate += "\n" + init + ": \n\tif (!(" + $3.translate + ")) goto " + end + ";";
						$$.translate += $5.translate;
						$$.translate += $3.auxOp;
						$$.translate += "\n\tgoto " + init + ";";
						$$.translate += "\n" + end + ":";
						$$.translate += $7.translate;
					}else{
						yyerror(INC_TYPE);
					}
				}
				| TK_BREAK ';'{
					string label;
					if (getContextLabelEnd(label))
						$$.translate = "\n\tgoto " + label + ";";
					else
						yyerror(COMANDO_INC);
				}
				| TK_CONTINUE ';'{
					string label;
					if (getContextLabelMiddle(label))
						$$.translate = "\n\tgoto " + label + ";";
					else
						yyerror(COMANDO_INC);
				}
				| TK_READ '(' ID IDS ')' ';'{
					$$.translate = $3.auxOp + $4.auxOp;
					$$.auxOp = "";
					Node node;
					string auxVar;
					if ($3.isArray) {
						if ($3.type == "char*"){
							updateVarSize($3.translate, 1024);
							$$.translate += "\n\t" + $3.translate + " = (char*)malloc(1024);";
						}
						$$.translate += "\n\tcin >> " + $3.translate + " >> " + $4.translate + ";";
					}else{
						if (getContextVariable($3, node)){
							if ($3.type == "char*"){
								updateVarSize(node.realVar, 1024);
								$$.translate += "\n\t" + node.alias + " = (char*)malloc(1024);";
							}
							$$.translate += "\n\tcin >> " + node.alias + " >> " + $4.translate + ";";
						}else{
							yyerror(VAR_NOT_DECLA);
						}
					}
				}
				| TK_WRITE '(' E OUTS ')' ';' {
					$$.translate = $3.auxOp + $4.auxOp;
					$$.translate += "\n\tcout << " + $3.translate + $4.translate + " << endl;";
				}
				;

CASES			: TK_CASE E ':' COMANDOS CASES {
					$$.params = $5.params;
					$$.dimensions = $5.dimensions;
					$$.auxOp = $2.auxOp + $5.auxOp;
					pair<string, string> p ($2.translate, $4.translate);
					$$.params.insert($$.params.begin(), p);
					$$.dimensions.insert($$.dimensions.begin(), $2.type);
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
					$$.params.clear();
					$$.dimensions.clear();
				};

OUTS 			: ',' E OUTS {
					$$.auxOp = $2.auxOp + $3.auxOp;
					$$.translate = " << " + $2.translate + $3.translate;
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
				}
				;

DECLARACOES		: TIPO TK_ID DECLARACOES_MAIS {
					if (!isAlreadyDeclaredInContext($2.translate)){
						$$.translate = $3.translate;
						$$.auxOp = $1.auxOp + $3.auxOp;
						if ($1.isArray){
							insertTable($2.translate, $1.type + "[]", $1.dimensions.size(), true, false);
							setDimensions($2.translate, $1.dimensions);
							$$.auxOp += "\n\t" + $1.type + " " + getLastTemp() + "[" + $1.translate + "];";
						}else{
							insertTable($2.translate, $1.type, 0, false, false);
						}
					} else {
						yyerror(ERROR_DECLA);
					}
				
				}
				| TIPO TK_ID TK_ASSIGN E DECLARACOES_MAIS { //tem que botar pra vetor
					if (!canOperateAssign($1, $4)){
						yyerror(ATTR_INVAL);
					}

					if (!isAlreadyDeclaredInContext($2.translate)) {
						$$.auxOp = $1.auxOp + $4.auxOp;
						if ($1.isArray){
							insertTable($2.translate, $1.type + "[]", $1.dimensions.size(), true, false);
							setDimensions($2.translate, $1.dimensions);
							$$.auxOp += "\n\t" + $1.type + " " + getLastTemp() + "[" + $1.translate + "];";
						    istringstream iss($4.translate);
						    int i;
						    for (i = 0; iss; i++) {
						    	stringstream stream;
						    	stream << i;
						        string word;
						        iss >> word;
						        if (word == "") break;
						        $$.auxOp += "\n\t" + getLastTemp() + "[" + stream.str() + "] = " + word + ";";
						    }

						    if (i != vetFlag) {
						    	yyerror(INC_VET_SIZE);
						    }

						    for (int i = 0; i < vetTypes.size(); i++){
						    	if (vetTypes[i] != $1.type)
						    		yyerror(INC_TYPE);
						    }
						    vetTypes.clear();
						    vetFlag = 1;
							$$.translate = $5.translate;
						} else {
							int size;
							getContextSizeVariable($4.translate, size);
							insertTable($2.translate, $1.type, size, false, false);
							$$.translate = "\n\t" + getLastTemp() + " = " + $4.translate + ";" + $5.translate;
							$$.auxOp += $1.auxOp + $4.auxOp + $5.auxOp;
						}
					} else {
						yyerror(ERROR_DECLA);
					}
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
				}
				;

DECLARACOES_MAIS: ',' TK_ID DECLARACOES_MAIS {
					if (!isAlreadyDeclaredInContext($2.translate)){
						$$.translate = $3.translate;
						$$.auxOp = $2.auxOp;
						if ($0.isArray){
							insertTable($2.translate, $1.type + "[]", $1.dimensions.size(), true, false);
							$$.auxOp += "\n\t" + $1.type + " " + getLastTemp() + "[" + $1.translate + "];";
						}else{
							insertTable($2.translate, $1.type, 0, false, false);
						}
						
					}else{
						yyerror(ERROR_DECLA);
					}
				}
				| ',' TK_ID TK_ASSIGN E DECLARACOES_MAIS {
					if (!isAlreadyDeclaredInContext($2.translate)){
						insertTable($2.translate, $1.type, 0, false, false);
						$$.translate = $4.auxOp + "\n\t" + getLastTemp() + " = " + $4.translate + ";" + $5.translate;
					}else{
						yyerror(ERROR_DECLA);
					}
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
				}
				;

ID 				: TK_ID ARRAY_ASSEC {
					$$.auxOp = "";
					Node node;
					string auxVar;
					if ($2.isArray) {
						if (getContextVariable($1, node)) {
							vector<string> *dims = getVariableDim($1.translate);
							$$.type = node.type.substr(0, node.type.size() - 2);

							if ((*dims).size() != $2.dimensions.size()){
								yyerror(INC_TYPE);
							}

							$$.auxOp += $2.auxOp;

							insertTable("", "int", 0, false, false);

							auxVar = getLastTemp();
							
							$$.auxOp += "\n\t" + auxVar + " = 0;";

							for (int i = $2.dimensions.size()-1; i > 0; i--) {
								$$.auxOp += "\n\t" + auxVar + " += " + $2.dimensions[i] + " * " + (*dims)[i] + ";";
							}
							$$.auxOp += "\n\t" + auxVar + " += " + $2.dimensions[0] + ";";
							$$.isArray = true;
							$$.translate = node.alias + "[" + auxVar + "]";
						}else{
							yyerror(VAR_NOT_DECLA);
						}
					}else{
						$$.isArray = false;
						$$.translate = $1.translate;
					}
				}
				;

ARRAY_ASSEC		: TK_COLCH_OPEN E TK_COLCH_CLOSE ARRAY_ASSEC { //Rever isso aki
					$$.isArray = true;
					if ($2.type == "int") {
						$$.dimensions = $4.dimensions;
						$$.type = $2.type;
						$$.auxOp = $2.auxOp + $4.auxOp;
						if (isIntVariable($2.translate)){
							$$.dimensions.push_back($2.translate);
						}else{
							yyerror(DIM_ERRO_NO_VAL); //deve ser inteiro;
						}
					}else{
						yyerror(DIM_ERRO); //deve ser inteiro;
					}
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
					$$.isArray = false;
				}
				;

IDS 			: ',' ID IDS { //tem que botar pra vetor aki
					Node node;
					if ($2.isArray){
						$$.translate = $2.translate;
						$$.auxOp = $$.auxOp;
						$$.type = $2.type;
					} else if (getContextVariable($2, node)){
						if (node.isVet){
							$$.type = node.type.substr(0, node.type.size() - 2);
						}else{
							$$.type = node.type;
						}
						$$.translate = node.alias;
						$$.auxOp = "";
					}else{
						yyerror(VAR_NOT_DECLA);
					}
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
				}
				;

TIPO 			: TK_TIPO ARRAY {
					$$.auxOp = $2.auxOp;
					$$.type = $1.type;
					$$.translate = $2.translate;
					$$.isArray = $2.isArray;
					$$.dimensions = $2.dimensions;
				}
				;

ARRAY			: TK_COLCH_OPEN E TK_COLCH_CLOSE ARRAY {
					$$.isArray = true;
					string variable;
					if ($2.type == "int"){
						$$.dimensions = $4.dimensions;
						$$.type = $2.type;
						if (isIntVariable($2.translate)){
							$$.dimensions.push_back($2.translate);
						}else{
							yyerror(DIM_ERRO_NO_VAL); //deve ser inteiro;
						}

						if ($4.translate != ""){
							insertTable("", $2.type, 0, false, false);
							variable = getLastTemp();
							$$.auxOp = $2.auxOp + $4.auxOp;
							$$.auxOp += "\n\t" + variable + " = " + $2.translate + " * " + $4.translate + ";";
							$$.translate = variable;
						}else{
							$$.auxOp = $2.auxOp + $4.auxOp;
							$$.translate = $2.translate;
						}
					}else{
						yyerror(DIM_ERRO); //deve ser inteiro;
					}
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
					$$.isArray = false;
				}
				;

VALUE 			: E VALUES {
					$$.params = $2.params;
					$$.auxOp = $1.auxOp + $2.auxOp;
					pair<string, string> p ($1.type, $1.translate);
					$$.params.insert($$.params.begin(), p);
				};

VALUES 			: ',' E VALUES {
					vetFlag++;
					vetTypes.push_back($2.type);
					$$.params = $3.params;
					$$.auxOp = $2.auxOp + $3.auxOp;
					pair<string, string> p ($2.type, $2.translate);
					$$.params.insert($$.params.begin(), p);
				}
				| {
					$$.translate = "";
					$$.auxOp = "";
					$$.params.clear();
				};

E 				: E TK_OP1 E {
					string variable, type;
					if (canOperate($1, $3, $2.translate, type)){
						insertTable("", type, 0, false, false);
						variable = getLastTemp();
						$$.auxOp = $1.auxOp + $3.auxOp;
						if($2.translate == "&" && $1.type == "char*" && $3.type == "char*"){
							contatString ($$, $1, $3, variable);
						}else{
							$$.auxOp += "\n\t" + variable + " = " + $1.translate + " " + $2.translate + " " + $3.translate + ";";
						}
						$$.translate = variable;
					}else{
						yyerror(INVAL_OP);
					}
				}
				| E TK_OP2 E {
					string variable, type;
					if (canOperate($1, $3, $2.translate, type)){
						insertTable("", type, 0, false, false);
						variable = getLastTemp();
						$$.auxOp = $1.auxOp + $3.auxOp;
						$$.auxOp += "\n\t" + variable + " = " + $1.translate + " " + $2.translate + " " + $3.translate + ";";
						$$.translate = variable;
					}else{
						yyerror(INVAL_OP);
					}
				}
				| TK_NOT E {
					$$.auxOp = $2.auxOp;
					$$.translate = "!(" + $2.translate + ")";
					$$.type = $2.type;
				}
				| RELATIONAL {
					$$.auxOp = $1.auxOp;
					$$.translate = $1.translate;
					$$.type = $1.type;
				}
				| LOGICAL {
					$$.auxOp = $1.auxOp;
					$$.translate = $1.translate;
					$$.type = $1.type;
				}
				| ID TK_PP {
					Node node;
					if ($1.isArray && ($1.type == "int" || $1.type == "double")){
						$$.translate = "\n\t" + $1.translate + $2.translate + ";";
						$$.auxOp = $$.auxOp;
						$$.type = $1.type;
					} else {
						if (getContextVariable($1, node)){
							if (node.type == "int" || node.type == "double") {
								$$.translate = "\n\t" + node.alias + $2.translate + ";";
								$$.type = $1.type;
							}else{
								yyerror(TIPO_INCON);
							}
						}else{
							yyerror(VAR_NOT_DECLA); 
						}
					}
				}
				| ID TK_MM {
					Node node;
					if ($1.isArray && ($1.type == "int" || $1.type == "double")){
						$$.translate = "\n\t" + $1.translate + $2.translate + ";";
						$$.auxOp = $$.auxOp;
						$$.type = $1.type;
					} else {
						if (getContextVariable($1, node)){
							if ($1.type == "int" || $1.type == "double"){
									$$.translate = "\n\t" + node.alias + $2.translate + ";";
									$$.type = $1.type;
							}else{
								yyerror(TIPO_INCON);
							}
						}else{
							yyerror(VAR_NOT_DECLA);
						}
					}
				}
				| '(' E ')'{
					$$.auxOp = $2.auxOp;
					$$.translate = $2.translate;
					$$.type = $2.type;
				}
				| ID {
					Node node;
					if ($1.isArray){
						$$.translate = $1.translate;
						$$.auxOp = $$.auxOp;
						$$.type = $1.type;
						$$.isArray = $1.isArray;
					} else {
						if (getContextVariable($1, node)){
							if (node.isVet){
								$$.type = node.type.substr(0, node.type.size() - 2);
							}else{
								$$.type = node.type;
							}
							$$.translate = node.alias;
							$$.auxOp = "";
						}else{
							yyerror(VAR_NOT_DECLA);
						}
					}
				}
				| TK_COLCH_OPEN VALUE TK_COLCH_CLOSE { //inicialização de vetor
					$$.auxOp = $2.auxOp;
					$$.translate = "";
					for (int i = 0; i < $2.params.size() - 1; i++) {
						$$.translate += $2.params[i].second + " ";
					}
					$$.translate += $2.params[$2.params.size() - 1].second;
				}
				| TK_NUM {
					insertTable("", $1.type, 0, false, false);
					$$.auxOp = "\n\t" + getLastTemp() + " = " + $1.translate + ";";
					$$.translate = getLastTemp();
					$$.type = $1.type;
				}
				| TK_STRING {
		 			insertTable("", $1.type, $1.translate.size() - 2, false, false);
					$$.auxOp = "\n\t" + getLastTemp() + " = " + $1.translate + ";";
					$$.translate = getLastTemp();
					$$.type = $1.type;
				}
				| TK_BOOL {
					insertTable("", $1.type, 0, false, false);
					$$.auxOp = "\n\t" + getLastTemp() + " = " + $1.translate + ";";
					$$.translate = getLastTemp();
					$$.type = $1.type;
				}
				| TK_CHAR {
					insertTable("", $1.type, 0, false, false);
					$$.auxOp = "\n\t" + getLastTemp() + " = '" + $1.translate + "';";
					$$.translate = getLastTemp();
					$$.type = $1.type;
				}
				| FUNC_CALL {
					FuncNode node;
					if (getContextFunction($1, node)) {
						string type, params = "(";
						$$.translate = "";
						$$.auxOp = $1.auxOp;
						for (int i = 0; i < $1.params.size(); i++){
							atributos *at1 = new atributos();
							at1->type = $1.params[i].first;
							at1->translate = $1.params[i].second;
							atributos *at2 = new atributos();
							at2->type = node.params[i].first;
							at2->translate = node.params[i].second;
							if (canOperateAssign(*at2, *at1)){
								$$.auxOp += at1->auxOp + at2->auxOp;
								params += at1->translate + ",";
							}else{
								yyerror(INC_PARAM); //botar função
							}
							$$.translate = node.alias + params.substr(0, params.size()-1) + ")";
						}
					}else{
						yyerror(FUN_NOT_DECLA); //botar função
					}
				}
				;

RELATIONAL		: E TK_REL_OP E {
					string type;

					if ($1.type == "char*" && $3.type == "char*" && $2.translate == "=="){
						$$.auxOp = $1.auxOp + $3.auxOp;
						insertTable("", "bool", 0, false, false);
						$$.auxOp += "\n\t" + getLastTemp() + " = !strcmp(" + $1.translate + ", " + $3.translate + ");";
						$$.translate = getLastTemp();
						$$.type = "bool";
					}else if (canOperate($1, $3, $2.translate, type)){
						insertTable("", type, 0, false, false);
						$$.auxOp = $1.auxOp + $3.auxOp;
						$$.auxOp += "\n\t" + getLastTemp() + " = " + $1.translate + " " + $2.translate + " " + $3.translate + ";";
						$$.translate = getLastTemp();
						$$.type = "bool";
					}else{
						yyerror(INVAL_OP);
					}
				}
				;
LOGICAL			: 	E TK_LOG_OP E {
					if ($1.type == "bool" && $3.type == "bool"){
						insertTable("", $1.type, 0, false, false);
						$$.auxOp = $1.auxOp + $3.auxOp;
						$$.auxOp += "\n\t" + getLastTemp() + " = " + $1.translate + " " + $2.translate + " " + $3.translate + ";";
						$$.translate = getLastTemp();
						$$.type = $1.type;
					}else{
						yyerror(INVAL_OP);
					}
				}
				;

ATRIBUICAO		: ID TK_ASSIGN E {
					Node node;
					if ($1.isArray){
						$$.translate = $3.auxOp + "\n\t" + $1.translate + " = " + $3.translate + ";";
					}else if (getContextVariable($1, node)){
						//cout << "type1: " << $1.type << " type2: " << $3.type << endl;
						if (!canOperateAssign($1, $3)){
							yyerror(ATTR_INVAL);
						}
						$$.translate = $3.auxOp + "\n\t" + node.alias + " = " + $3.translate + ";";
					}else{
						yyerror(VAR_NOT_DECLA);
					}
				}
				;

%%

#include "lex.yy.c"

int yyparse();

int main(int argc, char* argv[]){
	j = i = 0;
	currentContext = -1;
	line = 1;
	setConversions();
	insertContext(false);
	yyparse();
	return 0;
}

void yyerror(string MSG){
	cout << MSG << ", na linha "<< line << ", better call Saul!!!" << endl;
	exit (0);
}

bool isAlreadyDeclaredInContext(string name){
	//for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[currentContext].tabela.size(); j++){
			if (name == contexts[currentContext].tabela[j].realVar)
				return true;
		}
	//}

	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].funcoes.size(); j++){
			if (name == contexts[i].funcoes[j].realName)
				return true;
		}
	}
	return false;
}

string variableGenerate(){
	stringstream nome;
	nome << "temp" << i;
	i++;
	return nome.str();
}

string LabelGenerate(){
	stringstream nome;
	if (j % 2 == 0)
		nome << "LABEL_" << j;
	else
		nome << "LABEL_EXIT_" << j;
	j++;
	return nome.str();
}

string CaseLabelGenerate(){
	stringstream nome;
	nome << "CASE_" << j;
	j++;
	return nome.str();
}

string CommonLabelGenerate(){
	stringstream nome;
	nome << "MIDDLE_LABEL_" << j;
	return nome.str();
}

void insertTable(string realVar, string type, int size, bool isVet, bool isParam){
	string v = variableGenerate();
	Node declaracao;
	declaracao.realVar = realVar;
	declaracao.type  = type;
	declaracao.alias = v;
	declaracao.size = size;
	declaracao.isVet = isVet;
	declaracao.isParam = isParam;
	contexts[currentContext].tabela.push_back(declaracao);
}

void insertFuncTable(string realName, string type, bool isVet, vector<pair<string, string> > params){
	string v = variableGenerate();
	FuncNode declaracao;
	declaracao.realName = realName;
	declaracao.type  	= type;
	declaracao.alias 	= v;
	declaracao.isVet 	= isVet;
	declaracao.params 	= params;
	contexts[currentContext].funcoes.push_back(declaracao);
}

string getLastTemp(){
	return contexts[currentContext].tabela.back().alias;
}

string getLastFuncTemp(){
	return contexts[currentContext].funcoes.back().alias;
}

void insertContext(bool isLoop){
	NodeContext context;
	context.isLoop 		= isLoop;
	context.labelInit 	= LabelGenerate();
	context.labelMiddle = CommonLabelGenerate();
	context.labelEnd  	= LabelGenerate();
	contexts.insert(contexts.begin() + ++currentContext, context);
}

bool getContextVariable(atributos &at, Node &node) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (at.translate == contexts[i].tabela[j].realVar){
				node = contexts[i].tabela[j];
				at.type = contexts[i].tabela[j].type;
				return true;
			}
		}
	}
	return false;
}

bool getContextFunction(atributos &at, FuncNode &node) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].funcoes.size(); j++){
			if (at.translate == contexts[i].funcoes[j].realName){
				node = contexts[i].funcoes[j];
				at.type = contexts[i].funcoes[j].type;
				return true;
			}
		}
	}
	return false;
}

void updateVarSize(string name, int newSize) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (name == contexts[i].tabela[j].realVar){
				contexts[i].tabela[j].size = newSize;
			}
		}
	}
}

bool getContextLabelInit(string &label){
	for (int i = currentContext; i >= 0; i--){
		if (contexts[i].isLoop){
			label = contexts[i].labelInit;
			return true;
		}
	}
	return false;
}

vector<string> *getVariableDim(string name) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (name == contexts[i].tabela[j].realVar){
				return &(contexts[i].tabela[j].dimensions);
			}
		}
	}
	return NULL;
}

bool getContextLabelMiddle(string &label){
	for (int i = currentContext; i >= 0; i--){
		if (contexts[i].isLoop){
			label = contexts[i].labelMiddle;
			return true;
		}
	}
	return false;
}

bool getContextLabelEnd(string &label){
	for (int i = currentContext; i >= 0; i--){
		if (contexts[i].isLoop){
			label = contexts[i].labelEnd;
			return true;
		}
	}
	return false;
}

string printAllVariable(bool clear){
	string vars = "";
	for (int i = 0; i < contexts.size(); i++){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (!contexts[i].tabela[j].isVet && !contexts[i].tabela[j].isParam){
				vars += "\n\t" + contexts[i].tabela[j].type + " " + contexts[i].tabela[j].alias + ";";
				if (clear){
					contexts[i].tabela.erase(contexts[i].tabela.begin() + j);
					j--;
				}
			}
		}
	}
	return vars;
}

bool canOperate(atributos &at1, atributos &at2, string op, string &type){
	for (int i = 0; i < convertions.size(); i++){
		if (at1.type == at2.type) {
			type = at1.type;
			return true;
		}
		if ((at1.type == "char*" && at2.type != "char*") || (at2.type == "char*" && at1.type != "char*")){
			string convert;
			insertTable("", "char*", 0, false, false);
			if (at2.type != "char*"){
				if (at2.type == "int" || "bool") convert = ", \"%d\", ";
				if (at2.type == "double") convert = ", \"%lf\", ";
				at2.auxOp += "\n\t" + getLastTemp() + " = (char*)malloc(sizeof(1024));";
				at2.auxOp += "\n\tsprintf(" + getLastTemp() + convert + at2.translate + ");";
				at2.translate = getLastTemp();
				at2.type = "char*";
			}else{
				if (at1.type == "int" || "bool") convert = ", \"%d\", ";
				if (at1.type == "double") convert = ", \"%lf\", ";
				at1.auxOp += "\n\t" + getLastTemp() + " = (char*)malloc(sizeof(1024));";
				at1.auxOp += "\n\tsprintf(" + getLastTemp() + convert + at1.translate + ");";
				at1.translate = getLastTemp();
				at1.type = "char*";
			}
			type = "char*";
			return true;
		}
		if (convertions[i].op1 == at1.type && convertions[i].op2 == at2.type && convertions[i].op == op) {
			insertTable("", convertions[i].cType, 0, false, false);
			at2.auxOp += "\n\t" + getLastTemp() + " = ((" + convertions[i].cType + ")(" + at2.translate + "));";
			at2.translate = getLastTemp();
			type = convertions[i].type;
			return true;
		}

		if (convertions[i].op1 == at2.type && convertions[i].op2 == at1.type && convertions[i].op == op) {
			insertTable("", convertions[i].cType, 0, false, false);
			at1.auxOp += "\n\t" + getLastTemp() + " = ((" + convertions[i].cType + ")(" + at1.translate + "));";
			at1.translate = getLastTemp();
			type = convertions[i].type;
			return true;
		}
	}
	return false;
}

bool canOperateAssign(atributos &at1, atributos &at2) {
	if (at1.type == at2.type) return true;
	for (int i = 0; i < convertions.size(); i++){
		if (convertions[i].op1 == at1.type && convertions[i].op2 == at2.type && convertions[i].op == "<-") {
			insertTable("", convertions[i].type, 0, false, false);
			at2.auxOp += "\n\t" + getLastTemp() + " = ((" + convertions[i].type + ")(" + at2.translate + "));";
			at2.translate = getLastTemp();
			return true;
		}
	}
	return false;
}

void setDimensions(string name, vector<string> dimensions) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (name == contexts[i].tabela[j].realVar){
				contexts[i].tabela[j].dimensions = dimensions;
			}
		}
	}
}

bool getContextSizeVariable(string name, int &size) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++) {
			if (name == contexts[i].tabela[j].alias){
				size = contexts[i].tabela[j].size;
				return true;
			}
		}
	}
	return false;
}

void contatString(atributos &at, atributos at1, atributos at2, string variable) {
	int size1, size2;
	getContextSizeVariable(at1.translate, size1);
	getContextSizeVariable(at2.translate, size2);
	if (size1 == 0) size1 = 1000;
	if (size2 == 0) size2 = 1000;
	int aInt = size1 + size2 + 1;
	insertTable("", "int", 0, false, false);
	char str[aInt];
	sprintf(str, "%d", aInt);
	at.auxOp += "\n\t" + getLastTemp() + " = " + str + ";";
	at.auxOp += "\n\t" + variable + " = (char*)malloc(" + getLastTemp() + ");";
	at.auxOp += "\n\tstrcpy(" + variable + ", " + at1.translate + ");";
	at.auxOp += "\n\tstrcat(" + variable + ", " + at2.translate + ")" + ";";
}

bool isIntVariable(string name) {
	for (int i = currentContext; i >= 0; i--){
		for (int j = 0; j < contexts[i].tabela.size(); j++){
			if (name == contexts[i].tabela[j].alias && contexts[i].tabela[j].type == "int") {
				return true;
			}
		}
	}
	return false;
}

//Função que contem os tipos compativeis
void setConversions(){
	Conversions c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15;
	Conversions c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30;
	Conversions c31, c32, c33, c34, c35, c36, c37, c38, c39, c40, c41, c42, c43, c44, c45;
	/*int*/
	c1.op1 = "int";
	c1.op2 = "int"; //sempre colocar o tipo dominando na op2
	c1.op  = ">";
	c1.type = "bool";
	c1.cType = "int";

	c2.op1 = "int ";
	c2.op2 = "int "; //sempre colocar o tipo dominando na op2
	c2.op  = ">=";
	c2.type = "bool";
	c2.cType = "int";

	c3.op1 = "int";
	c3.op2 = "int"; //sempre colocar o tipo dominando na op2
	c3.op  = "<";
	c3.type = "bool";
	c3.cType = "int";

	c4.op1 = "int";
	c4.op2 = "int"; //sempre colocar o tipo dominando na op2
	c4.op  = "<=";
	c4.type = "bool";
	c4.cType = "int";

	c5.op1 = "int";
	c5.op2 = "int"; //sempre colocar o tipo dominando na op2
	c5.op  = "==";
	c5.type = "bool";
	c5.cType = "int";

	c6.op1 = "int";
	c6.op2 = "int"; //sempre colocar o tipo dominando na op2
	c6.op  = "<>";
	c6.type = "bool";
	c6.cType = "int";

	c7.op1 = "int";
	c7.op2 = "int"; //sempre colocar o tipo dominando na op2
	c7.op  = "+";
	c7.type = "int";
	c7.cType = "int";

	c8.op1 = "int";
	c8.op2 = "int"; //sempre colocar o tipo dominando na op2
	c8.op  = "%";
	c8.type = "int";
	c8.cType = "int";

	c9.op1 = "int";
	c9.op2 = "int"; //sempre colocar o tipo dominando na op2
	c9.op  = "-";
	c9.type = "int";
	c9.cType = "int";

	c10.op1 = "int";
	c10.op2 = "int"; //sempre colocar o tipo dominando na op2
	c10.op  = "/";
	c10.type = "int";
	c10.cType = "int";

	c11.op1 = "int";
	c11.op2 = "int"; //sempre colocar o tipo dominando na op2
	c11.op  = "*";
	c11.type = "int";
	c11.cType = "int";

	/*int*/
	/*double*/
	c12.op1 = "double";
	c12.op2 = "double"; //sempre colocar o tipo dominando na op2
	c12.op  = ">";
	c12.type = "bool";
	c12.cType = "int";

	c13.op1 = "double ";
	c13.op2 = "double "; //sempre colocar o tipo dominando na op2
	c13.op  = ">=";
	c13.type = "bool";
	c13.cType = "int";

	c14.op1 = "double";
	c14.op2 = "double"; //sempre colocar o tipo dominando na op2
	c14.op  = "<";
	c14.type = "bool";
	c14.cType = "int";

	c15.op1 = "double";
	c15.op2 = "double"; //sempre colocar o tipo dominando na op2
	c15.op  = "<=";
	c15.type = "bool";
	c15.cType = "int";

	c16.op1 = "double";
	c16.op2 = "double"; //sempre colocar o tipo dominando na op2
	c16.op  = "==";
	c16.type = "bool";
	c16.cType = "int";

	c17.op1 = "double";
	c17.op2 = "double"; //sempre colocar o tipo dominando na op2
	c17.op  = "<>";
	c17.type = "bool";
	c17.cType = "int";

	c18.op1 = "double";
	c18.op2 = "double"; //sempre colocar o tipo dominando na op2
	c18.op  = "+";
	c18.type = "double";
	c18.cType = "double";

	c19.op1 = "double";
	c19.op2 = "double"; //sempre colocar o tipo dominando na op2
	c19.op  = "-";
	c19.type = "double";
	c19.cType = "double";

	c20.op1 = "double";
	c20.op2 = "double"; //sempre colocar o tipo dominando na op2
	c20.op  = "/";
	c20.type = "double";
	c20.cType = "double";

	c21.op1 = "double";
	c21.op2 = "double"; //sempre colocar o tipo dominando na op2
	c21.op  = "*";
	c21.type = "double";
	c21.cType = "double";

	c22.op1 = "double";
	c22.op2 = "double"; //sempre colocar o tipo dominando na op2
	c22.op  = "%";
	c22.type = "int";
	c22.cType = "int";
	/*double*/

	/*double-int*/
	c23.op1 = "int";
	c23.op2 = "double"; //sempre colocar o tipo dominando na op2
	c23.op  = ">";
	c23.type = "bool";
	c23.cType = "int";

	c24.op1 = "int";
	c24.op2 = "double"; //sempre colocar o tipo dominando na op2
	c24.op  = ">=";
	c24.type = "bool";
	c24.cType = "int";

	c25.op1 = "int";
	c25.op2 = "double"; //sempre colocar o tipo dominando na op2
	c25.op  = "<";
	c25.type = "bool";
	c25.cType = "int";

	c26.op1 = "int";
	c26.op2 = "double"; //sempre colocar o tipo dominando na op2
	c26.op  = "<=";
	c26.type = "bool";
	c26.cType = "int";

	c27.op1 = "int";
	c27.op2 = "double"; //sempre colocar o tipo dominando na op2
	c27.op  = "==";
	c27.type = "bool";
	c27.cType = "int";

	c28.op1 = "int";
	c28.op2 = "double"; //sempre colocar o tipo dominando na op2
	c28.op  = "<>";
	c28.type = "bool";
	c28.cType = "int";
	/*double-int*/

	/*double-int-aritm*/
	c29.op1 = "int";
	c29.op2 = "double"; //sempre colocar o tipo dominando na op2
	c29.op  = "+";
	c29.type = "double";
	c29.cType = "double";

	c30.op1 = "int";
	c30.op2 = "double"; //sempre colocar o tipo dominando na op2
	c30.op  = "-";
	c30.type = "double";
	c30.cType = "double";

	c31.op1 = "int";
	c31.op2 = "double"; //sempre colocar o tipo dominando na op2
	c31.op  = "/";
	c31.type = "double";
	c31.cType = "double";

	c32.op1 = "int";
	c32.op2 = "double"; //sempre colocar o tipo dominando na op2
	c32.op  = "*";
	c32.type = "double";
	c32.cType = "double";

	c33.op1 = "int";
	c33.op2 = "double"; //sempre colocar o tipo dominando na op2
	c33.op  = "%";
	c33.type = "int";
	c33.cType = "double";
	/*doble-int-aritm*/

	c34.op1 = "char*";
	c34.op2 = "char*";
	c34.op = "&";
	c34.type = "char*";
	c34.cType = "char*";

	c35.op1 = "int";
	c35.op2 = "char*";
	c35.op = "&";
	c35.type = "char*";
	c35.cType = "char*";

	/*atribuições*/
	c36.op1 = "int";
	c36.op2 = "int"; //sempre colocar o tipo dominando na op2
	c36.op  = "<-";
	c36.type = "int";
	c36.cType = "int";

	c37.op1 = "double";
	c37.op2 = "double"; //sempre colocar o tipo dominando na op2
	c37.op  = "<-";
	c37.type = "double";
	c37.cType = "double";

	c38.op1 = "int";
	c38.op2 = "double"; //sempre colocar o tipo dominando na op2
	c38.op  = "<-";
	c38.type = "int";
	c38.cType = "int";

	c39.op1 = "double";
	c39.op2 = "int"; //sempre colocar o tipo dominando na op2
	c39.op  = "<-";
	c39.type = "double";
	c39.cType = "double";
	/*atribuições*/

	convertions.push_back(c1);
	convertions.push_back(c2);
	convertions.push_back(c3);
	convertions.push_back(c4);
	convertions.push_back(c5);
	convertions.push_back(c6);
	convertions.push_back(c7);
	convertions.push_back(c8);
	convertions.push_back(c9);
	convertions.push_back(c10);
	convertions.push_back(c11);
	convertions.push_back(c12);
	convertions.push_back(c13);
	convertions.push_back(c14);
	convertions.push_back(c15);
	convertions.push_back(c16);
	convertions.push_back(c17);
	convertions.push_back(c18);
	convertions.push_back(c19);
	convertions.push_back(c20);
	convertions.push_back(c21);
	convertions.push_back(c22);
	convertions.push_back(c23);
	convertions.push_back(c24);
	convertions.push_back(c25);
	convertions.push_back(c26);
	convertions.push_back(c27);
	convertions.push_back(c28);
	convertions.push_back(c29);
	convertions.push_back(c30);
	convertions.push_back(c31);
	convertions.push_back(c32);
	convertions.push_back(c33);
	convertions.push_back(c34);
	convertions.push_back(c35);
	convertions.push_back(c36);
	convertions.push_back(c37);
	convertions.push_back(c38);
	convertions.push_back(c39);
}