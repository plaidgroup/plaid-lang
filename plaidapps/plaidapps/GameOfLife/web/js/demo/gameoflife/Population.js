var plaidNewState_Population = new PlaidState();
plaidNewState_Population.tree = [['', [], 'with'], [['Population', [], 'with']]];
var plaidNewState_Populated = new PlaidState();
plaidNewState_Populated['onPopulated'] = function onPopulated () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_AliveCell);
return this$plaid;
}
plaidNewState_Populated.tree = [['', [], 'with'], [['Population', [], 'with'], [['Populated', ['onPopulated'], '']]]];
var plaidNewState_Survive = new PlaidState();
plaidNewState_Survive['onSurvive'] = function onSurvive () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_AliveCell);
return this$plaid;
}
plaidNewState_Survive.tree = [['', [], 'with'], [['Population', [], 'with'], [['Survive', ['onSurvive'], '']]]];
var plaidNewState_Lonely = new PlaidState();
plaidNewState_Lonely['onLonely'] = function onLonely () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_DeadCell);
return this$plaid;
}
plaidNewState_Lonely.tree = [['', [], 'with'], [['Population', [], 'with'], [['Lonely', ['onLonely'], '']]]];
var plaidNewState_OverPopulated = new PlaidState();
plaidNewState_OverPopulated['onOverPopulated'] = function onOverPopulated () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_DeadCell);
return this$plaid;
}
plaidNewState_OverPopulated.tree = [['', [], 'with'], [['Population', [], 'with'], [['OverPopulated', ['onOverPopulated'], '']]]];
var plaidNewState_Unpopulated = new PlaidState();
plaidNewState_Unpopulated['onUnpopulated'] = function onUnpopulated () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_DeadCell);
return this$plaid;
}
plaidNewState_Unpopulated.tree = [['', [], 'with'], [['Population', [], 'with'], [['Unpopulated', ['onUnpopulated'], '']]]];
