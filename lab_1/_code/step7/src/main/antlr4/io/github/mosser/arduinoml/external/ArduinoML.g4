grammar ArduinoML;


/******************
 ** Parser rules **
 ******************/


app         :   'application' name=IDENTIFIER '{' sensor*  (actuator | sevenSeg)+ state+ '}';

sensor      :   'sensor'   location ;
actuator    :   'actuator' location ;
sevenSeg    :   'sevenSeg' id=IDENTIFIER ':' '[' (PORT_NUMBER ',')* PORT_NUMBER ']' ;
location    :   id=IDENTIFIER ':' port=PORT_NUMBER;

state       :   initial? name=IDENTIFIER sensorName=IDENTIFIER? '{'  action+ next nextIfHigh?'}';
action      :   actuatorAction | sevenSegAction;
actuatorAction :   receiver=IDENTIFIER 'is' value=SIGNAL;
sevenSegAction :   receiver=IDENTIFIER 'is' value=SEVENSEGNUM;
map         :   key=INT '=>' nextState=INT ','?;
next :   'goto' target=IDENTIFIER ;
nextIfHigh  :   'ifHighGoto' target=IDENTIFIER ;
initial     :   '->';

/*****************
 ** Lexer rules **
 *****************/

PORT_NUMBER     :   [1-9] | '10' | '11' | '12' | '13';
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIGNAL          :   'HIGH' | 'LOW';
SEVENSEGNUM     :   'ZERO' | 'ONE' | 'TWO' | 'THREE' | 'FOUR' | 'FIVE' | 'SIX' | 'SEVEN' | 'EIGHT' | 'NINE';

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
