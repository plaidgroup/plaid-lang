var plaidNewState_Timer = new PlaidState();
plaidNewState_Timer.tree = [['', [], 'with'], [['Timer', [], 'with']]];
var plaidNewState_PeriodicalTimer = new PlaidState();
plaidNewState_PeriodicalTimer.tree = [['', [], 'with'], [['Timer', [], 'with'], [['PeriodicalTimer', [], '']]]];
var plaidNewState_RunningPeriodicalTimer = new PlaidState();
plaidNewState_RunningPeriodicalTimer['onRunningperiodicaltimer'] = function onRunningperiodicaltimer () {
var this$plaid=this;
var plaidNewVar1 = window.setInterval(this$plaid.callback,this$plaid.interval);
this$plaid.timer = plaidNewVar1;
return this$plaid.timer;
}
plaidNewState_RunningPeriodicalTimer['offRunningPeriodicalTimer'] = function offRunningPeriodicalTimer () {
var this$plaid=this;
var plaidNewVar2 = window.clearInterval(this$plaid.timer);
return plaidNewVar2;
}
plaidNewState_RunningPeriodicalTimer.tree = [['', [], 'with'], [['Timer', [], 'with'], [['PeriodicalTimer', [], ''], [['RunningPeriodicalTimer', ['onRunningperiodicaltimer', 'offRunningPeriodicalTimer'], '']]]]];
var plaidNewState_StoppingPeriodicalTimer = new PlaidState();
plaidNewState_StoppingPeriodicalTimer.tree = [['', [], 'with'], [['Timer', [], 'with'], [['PeriodicalTimer', [], ''], [['StoppingPeriodicalTimer', [], '']]]]];
