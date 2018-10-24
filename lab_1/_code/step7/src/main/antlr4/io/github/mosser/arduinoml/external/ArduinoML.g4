grammar ArduinoML;


/******************
 ** Parser rules **
 ******************/


app         :   'application' name=IDENTIFIER '{' sensor*  actuator+ state+ '}';

sensor      :   'sensor'   location ;
actuator    :   'actuator' location ;
location    :   id=IDENTIFIER ':' port=PORT_NUMBER;

state       :   initial? name=IDENTIFIER sensorName=IDENTIFIER? '{'  action+ next nextIfHigh?'}';
action      :   actuatorAction | sevenSegAction;
actuatorAction :   receiver=IDENTIFIER 'is' value=SIGNAL;
sevenSegAction :   receiver=IDENTIFIER '[' map*  ']';
map         :   key=INT '=>' nextState=INT ','?;
next :   'goto' target=IDENTIFIER ;
nextIfHigh  :   'ifHighGoto' target=IDENTIFIER ;
initial     :   '->';

/*****************
 ** Lexer rules **
 *****************/

INT             :   [0-9];
PORT_NUMBER     :   [1-9] | '10' | '11' | '12' | '13';
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIGNAL          :   'HIGH' | 'LOW';

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
