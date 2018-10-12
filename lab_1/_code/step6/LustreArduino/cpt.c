/********
* ec2c version 0.67
* c file generated for node : cpt 
* context   method = HEAP
* ext call  method = PROCEDURES
********/
#include <stdlib.h>
#include <string.h>
#define _cpt_EC2C_SRC_FILE
#include "cpt.h"
/*--------
Internal structure for the call
--------*/
typedef struct  {
   void* client_data;
   //INPUTS
   _boolean _reset;
   //OUTPUTS
   _boolean _led_on;
   _integer _counter;
   //REGISTERS
   _integer M14;
   _boolean M14_nil;
   _boolean M7;
   _boolean M7_nil;
   _boolean M2;
} cpt_ctx;
/*--------
Output procedures must be defined,
Input procedures must be used:
--------*/
void cpt_I_reset(cpt_ctx* ctx, _boolean V){
   ctx->_reset = V;
}
extern void cpt_O_led_on(void* cdata, _boolean);
extern void cpt_O_counter(void* cdata, _integer);
#ifdef CKCHECK
extern void cpt_BOT_led_on(void* cdata);
extern void cpt_BOT_counter(void* cdata);
#endif
/*--------
Internal reset input procedure
--------*/
static void cpt_reset_input(cpt_ctx* ctx){
   //NOTHING FOR THIS VERSION...
}
/*--------
Reset procedure
--------*/
void cpt_reset(cpt_ctx* ctx){
   ctx->M14_nil = _true;
   ctx->M7_nil = _true;
   ctx->M2 = _true;
   cpt_reset_input(ctx);
}
/*--------
Copy the value of an internal structure
--------*/
void cpt_copy_ctx(cpt_ctx* dest, cpt_ctx* src){
   memcpy((void*)dest, (void*)src, sizeof(cpt_ctx));
}
/*--------
Dynamic allocation of an internal structure
--------*/
cpt_ctx* cpt_new_ctx(void* cdata){
   cpt_ctx* ctx = (cpt_ctx*)calloc(1, sizeof(cpt_ctx));
   ctx->client_data = cdata;
   cpt_reset(ctx);
   return ctx;
}
/*--------
Step procedure
--------*/
void cpt_step(cpt_ctx* ctx){
//LOCAL VARIABLES
   _boolean L6;
   _boolean L5;
   _boolean L1;
   _integer L13;
   _integer L12;
   _integer L11;
   _integer L9;
   _integer T14;
   _boolean T7;
//CODE
   L6 = (! ctx->M7);
   if (ctx->_reset) {
      L5 = L6;
   } else {
      L5 = ctx->M7;
   }
   if (ctx->M2) {
      L1 = _false;
   } else {
      L1 = L5;
   }
   cpt_O_led_on(ctx->client_data, L1);
   L13 = (ctx->M14 + 1);
   L12 = (L13 % 10);
   if (ctx->_reset) {
      L11 = 0;
   } else {
      L11 = L12;
   }
   if (ctx->M2) {
      L9 = 0;
   } else {
      L9 = L11;
   }
   cpt_O_counter(ctx->client_data, L9);
   T14 = L9;
   T7 = L1;
   ctx->M14 = T14;
   ctx->M14_nil = _false;
   ctx->M7 = T7;
   ctx->M7_nil = _false;
   ctx->M2 = ctx->M2 && !(_true);
}
