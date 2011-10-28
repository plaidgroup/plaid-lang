var plaidNewState_UIComponent = new PlaidState();
var plaidNewVar1 = function(id) {
var plaidNewVar2 = document.getElementById(id);
return plaidNewVar2;
};
plaidNewState_UIComponent['node'] = plaidNewVar1;
plaidNewState_UIComponent.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with']]];
var plaidNewState_Box = new PlaidState();
plaidNewState_Box['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Box.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Box', [], '']]]];
var plaidNewState_Accordion = new PlaidState();
plaidNewState_Accordion['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Accordion['init'] = function init () {
var this$plaid=this;
var plaidNewVar4 = "#" + this$plaid.id;
var plaidNewVar5 = jQuery(plaidNewVar4);
var plaidNewVar3 = plaidNewVar5.accordion();
return plaidNewVar3;
}
plaidNewState_Accordion.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Accordion', ['init'], '']]]];
var plaidNewState_TextBox = new PlaidState();
plaidNewState_TextBox['node'] = plaidNewState_UIComponent['node'];
plaidNewState_TextBox['init'] = function init () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_EmptyTextBox);
var vAr213$plaid = this$plaid;
var plaidNewVar6 = this$plaid.onEmpty();
var vAr212$plaid = plaidNewVar6;
var plaidNewVar7 = function() {
var plaidNewVar8 = this$plaid.node(this$plaid.id);
var plaidNewVar9 = this$plaid.update(plaidNewVar8.value);
var vAr209$plaid = plaidNewVar9;
var plaidNewVar11 = this$plaid.members();
var plaidNewVar10 = plaidNewVar11.toString();
var plaidNewVar15 = this$plaid.tags();
var plaidNewVar14 = plaidNewVar15.toString();
var plaidNewVar18 = this$plaid.node(this$plaid.id);
var plaidNewVar17 = plaidNewVar18.value.length + "<br />";
var plaidNewVar16 = plaidNewVar17 + plaidNewVar14;
var plaidNewVar13 = plaidNewVar16 + "<br />";
var plaidNewVar12 = plaidNewVar13 + plaidNewVar10;
str = plaidNewVar12;
var vAr208$plaid = str;
var plaidNewVar19 = DebugWriter.write(str);
return plaidNewVar19;
};
var f = plaidNewVar7;
var plaidNewVar21 = this$plaid.node(this$plaid.id);
var plaidNewVar20 = plaidNewVar21.addEventListener("keyup",f);
var vAr211$plaid = plaidNewVar20;
var plaidNewVar23 = this$plaid.node(this$plaid.id);
var plaidNewVar22 = plaidNewVar23.addEventListener("focusout",f);
var vAr210$plaid = plaidNewVar22;
var plaidNewVar24 = function() {
var plaidNewVar25 = this$plaid.node(this$plaid.id);
var plaidNewVar26 = this$plaid.update(plaidNewVar25.value);
return plaidNewVar26;
};
var plaidNewVar27 = window.setInterval(plaidNewVar24,100);
return plaidNewVar27;
}
plaidNewState_TextBox['update'] = function update (text) {
var this$plaid=this;
var plaidNewVar28 = function(vAr217$plaid) {
this$plaid.stateChange(plaidNewState_EmptyTextBox);
return this$plaid;
};
var plaidNewVar29 = function(vAr216$plaid) {
var plaidNewVar30 = function(vAr215$plaid) {
this$plaid.stateChange(plaidNewState_InvalidTextBox);
return this$plaid;
};
var plaidNewVar31 = function(vAr214$plaid) {
this$plaid.stateChange(plaidNewState_ValidTextBox);
return this$plaid;
};
var plaidNewVar32 = text.length <= this$plaid.max;
var plaidNewVar34 = this$plaid.min <= text.length;
var plaidNewVar33 = plaidNewVar34 && plaidNewVar32;
var plaidNewVar35 = ifElse(plaidNewVar33);
var plaidNewVar36 = plaidNewVar35(plaidNewVar31);
var plaidNewVar37 = plaidNewVar36(plaidNewVar30);
return plaidNewVar37;
};
var plaidNewVar38 = text.length > 0;
var plaidNewVar39 = ifElse(plaidNewVar38);
var plaidNewVar40 = plaidNewVar39(plaidNewVar29);
var plaidNewVar41 = plaidNewVar40(plaidNewVar28);
return plaidNewVar41;
}
plaidNewState_TextBox.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['TextBox', ['init', 'update'], '']]]];
var plaidNewState_EmptyTextBox = new PlaidState();
plaidNewState_EmptyTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_EmptyTextBox['init'] = plaidNewState_TextBox['init'];
plaidNewState_EmptyTextBox['update'] = plaidNewState_TextBox['update'];
plaidNewState_EmptyTextBox['onEmpty'] = function onEmpty () {
var this$plaid=this;
return undefined;
}
plaidNewState_EmptyTextBox['offEmpty'] = function offEmpty () {
var this$plaid=this;
return undefined;
}
plaidNewState_EmptyTextBox.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['TextBox', ['init', 'update'], ''], [['EmptyTextBox', ['onEmpty', 'offEmpty'], '']]]]];
var plaidNewState_InvalidTextBox = new PlaidState();
plaidNewState_InvalidTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_InvalidTextBox['init'] = plaidNewState_TextBox['init'];
plaidNewState_InvalidTextBox['update'] = plaidNewState_TextBox['update'];
plaidNewState_InvalidTextBox['validate'] = function validate () {
var this$plaid=this;
var plaidNewVar42 = returnFalse();
return plaidNewVar42;
}
plaidNewState_InvalidTextBox['onInvalid'] = function onInvalid () {
var this$plaid=this;
return undefined;
}
plaidNewState_InvalidTextBox['offInvalid'] = function offInvalid () {
var this$plaid=this;
return undefined;
}
plaidNewState_InvalidTextBox.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['TextBox', ['init', 'update'], ''], [['InvalidTextBox', ['validate', 'onInvalid', 'offInvalid'], '']]]]];
var plaidNewState_ValidTextBox = new PlaidState();
plaidNewState_ValidTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_ValidTextBox['init'] = plaidNewState_TextBox['init'];
plaidNewState_ValidTextBox['update'] = plaidNewState_TextBox['update'];
plaidNewState_ValidTextBox['validate'] = function validate () {
var this$plaid=this;
var plaidNewVar43 = returnTrue();
return plaidNewVar43;
}
plaidNewState_ValidTextBox['onValid'] = function onValid () {
var this$plaid=this;
return undefined;
}
plaidNewState_ValidTextBox['offValid'] = function offValid () {
var this$plaid=this;
return undefined;
}
plaidNewState_ValidTextBox.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['TextBox', ['init', 'update'], ''], [['ValidTextBox', ['validate', 'onValid', 'offValid'], '']]]]];
var plaidNewState_Button = new PlaidState();
plaidNewState_Button['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Button['init'] = function init () {
var this$plaid=this;
var plaidNewVar44 = function() {
var plaidNewVar45 = this$plaid.mousedown();
return plaidNewVar45;
};
var plaidNewVar47 = this$plaid.node(this$plaid.id);
var plaidNewVar46 = plaidNewVar47.addEventListener("mousedown",plaidNewVar44);
var vAr219$plaid = plaidNewVar46;
var plaidNewVar48 = function() {
var plaidNewVar49 = this$plaid.mouseup();
return plaidNewVar49;
};
var plaidNewVar51 = this$plaid.node(this$plaid.id);
var plaidNewVar50 = plaidNewVar51.addEventListener("mouseup",plaidNewVar48);
var vAr218$plaid = plaidNewVar50;
var plaidNewVar52 = function() {
var plaidNewVar53 = this$plaid.mouseout();
return plaidNewVar53;
};
var plaidNewVar55 = this$plaid.node(this$plaid.id);
var plaidNewVar54 = plaidNewVar55.addEventListener("mouseout",plaidNewVar52);
return plaidNewVar54;
}
plaidNewState_Button['mousedown'] = function mousedown () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_PressedButton);
return this$plaid;
}
plaidNewState_Button['mouseup'] = function mouseup () {
var this$plaid=this;
var matchVar = this$plaid;
if (matchVar.match("PressedButton")) {
var plaidNewVar56 = this$plaid.click();
var vAr220$plaid = plaidNewVar56;
this$plaid.stateChange(plaidNewState_UnpressedButton);
var returnVal = this$plaid;
}
return returnVal;
}
plaidNewState_Button['mouseout'] = function mouseout () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_UnpressedButton);
return this$plaid;
}
plaidNewState_Button.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], '']]]];
var plaidNewState_PressedButton = new PlaidState();
plaidNewState_PressedButton['node'] = plaidNewState_Button['node'];
plaidNewState_PressedButton['init'] = plaidNewState_Button['init'];
plaidNewState_PressedButton['mousedown'] = plaidNewState_Button['mousedown'];
plaidNewState_PressedButton['mouseup'] = plaidNewState_Button['mouseup'];
plaidNewState_PressedButton['mouseout'] = plaidNewState_Button['mouseout'];
plaidNewState_PressedButton['onPressedButton'] = function onPressedButton () {
var this$plaid=this;
return undefined;
}
plaidNewState_PressedButton['offPressedButton'] = function offPressedButton () {
var this$plaid=this;
return undefined;
}
plaidNewState_PressedButton.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], ''], [['PressedButton', ['onPressedButton', 'offPressedButton'], '']]]]];
var plaidNewState_UnpressedButton = new PlaidState();
plaidNewState_UnpressedButton['node'] = plaidNewState_Button['node'];
plaidNewState_UnpressedButton['init'] = plaidNewState_Button['init'];
plaidNewState_UnpressedButton['mousedown'] = plaidNewState_Button['mousedown'];
plaidNewState_UnpressedButton['mouseup'] = plaidNewState_Button['mouseup'];
plaidNewState_UnpressedButton['mouseout'] = plaidNewState_Button['mouseout'];
plaidNewState_UnpressedButton['onUnpressedButton'] = function onUnpressedButton () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnpressedButton['offUnpressedButton'] = function offUnpressedButton () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnpressedButton.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], ''], [['UnpressedButton', ['onUnpressedButton', 'offUnpressedButton'], '']]]]];
var plaidNewState_DropDownList = new PlaidState();
plaidNewState_DropDownList['node'] = plaidNewState_UIComponent['node'];
var plaidNewVar57 = returnArray();
plaidNewState_DropDownList['options'] = plaidNewVar57;
var plaidNewVar58 = function() {
return undefined;
};
plaidNewState_DropDownList['beforeChange'] = plaidNewVar58;
var plaidNewVar59 = function() {
return undefined;
};
plaidNewState_DropDownList['afterChange'] = plaidNewVar59;
plaidNewState_DropDownList['init'] = function init () {
var this$plaid=this;
var plaidNewVar60 = function(evt) {
var plaidNewVar61 = this$plaid.beforeChange();
var vAr224$plaid = plaidNewVar61;
var v = evt.target.value;
var plaidNewVar62 = function(o) {
var matchVar = o;
if (matchVar.match("SelectedOption")) {
o.stateChange(plaidNewState_UnselectedOption);
var returnVal = o;
}
var vAr222$plaid = returnVal;
var plaidNewVar63 = function(vAr221$plaid) {
o.stateChange(plaidNewState_SelectedOption);
return o;
};
var plaidNewVar64 = o.value == v;
var plaidNewVar65 = if$plaid(plaidNewVar64);
var plaidNewVar66 = plaidNewVar65(plaidNewVar63);
return plaidNewVar66;
};
var plaidNewVar67 = this$plaid.options.each(plaidNewVar62);
var vAr223$plaid = plaidNewVar67;
var plaidNewVar68 = this$plaid.afterChange();
return plaidNewVar68;
};
var plaidNewVar70 = this$plaid.node(this$plaid.id);
var plaidNewVar69 = plaidNewVar70.addEventListener("change",plaidNewVar60);
return plaidNewVar69;
}
plaidNewState_DropDownList['addOption'] = function addOption (option) {
var this$plaid=this;
var plaidNewVar71 = this$plaid.options.push(option);
return plaidNewVar71;
}
plaidNewState_DropDownList.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['DropDownList', ['options', 'beforeChange', 'afterChange', 'init', 'addOption'], '']]]];
var plaidNewState_Option = new PlaidState();
plaidNewState_Option['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Option['init'] = function init (parent) {
var this$plaid=this;
var plaidNewVar72 = parent.addOption(this$plaid);
return plaidNewVar72;
}
plaidNewState_Option.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Option', ['init'], '']]]];
var plaidNewState_SelectedOption = new PlaidState();
plaidNewState_SelectedOption['node'] = plaidNewState_Option['node'];
plaidNewState_SelectedOption['init'] = plaidNewState_Option['init'];
plaidNewState_SelectedOption['onSelectedOption'] = function onSelectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_SelectedOption['offSelectedOption'] = function offSelectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_SelectedOption.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Option', ['init'], ''], [['SelectedOption', ['onSelectedOption', 'offSelectedOption'], '']]]]];
var plaidNewState_UnselectedOption = new PlaidState();
plaidNewState_UnselectedOption['node'] = plaidNewState_Option['node'];
plaidNewState_UnselectedOption['init'] = plaidNewState_Option['init'];
plaidNewState_UnselectedOption['onUnselectedOption'] = function onUnselectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnselectedOption['offUnselectedOption'] = function offUnselectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnselectedOption.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Option', ['init'], ''], [['UnselectedOption', ['onUnselectedOption', 'offUnselectedOption'], '']]]]];
var plaidNewState_DatePicker = new PlaidState();
plaidNewState_DatePicker['node'] = plaidNewState_UIComponent['node'];
plaidNewState_DatePicker['init'] = function init () {
var this$plaid=this;
var plaidNewVar74 = "#" + this$plaid.id;
var plaidNewVar75 = jQuery(plaidNewVar74);
var plaidNewVar73 = plaidNewVar75.datepicker();
return plaidNewVar73;
}
plaidNewState_DatePicker.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['DatePicker', ['init'], '']]]];
var plaidNewState_Availability = new PlaidState();
plaidNewState_Availability.tree = [['', [], 'with'], [['Availability', [], 'with']]];
var plaidNewState_Enabled = new PlaidState();
plaidNewState_Enabled['onEnabled'] = function onEnabled () {
var this$plaid=this;
var plaidNewVar77 = this$plaid.node(this$plaid.id);
var plaidNewVar76 = plaidNewVar77.removeAttribute("disabled");
return plaidNewVar76;
}
plaidNewState_Enabled['offEnabled'] = function offEnabled () {
var this$plaid=this;
return undefined;
}
plaidNewState_Enabled.tree = [['', [], 'with'], [['Availability', [], 'with'], [['Enabled', ['onEnabled', 'offEnabled'], '']]]];
var plaidNewState_Disabled = new PlaidState();
plaidNewState_Disabled['onDisabled'] = function onDisabled () {
var this$plaid=this;
var plaidNewVar79 = this$plaid.node(this$plaid.id);
var plaidNewVar78 = plaidNewVar79.setAttribute("disabled","disabled");
return plaidNewVar78;
}
plaidNewState_Disabled['offDisabled'] = function offDisabled () {
var this$plaid=this;
return undefined;
}
plaidNewState_Disabled.tree = [['', [], 'with'], [['Availability', [], 'with'], [['Disabled', ['onDisabled', 'offDisabled'], '']]]];
var plaidNewState_Visibility = new PlaidState();
plaidNewState_Visibility.tree = [['', [], 'with'], [['Visibility', [], 'with']]];
var plaidNewState_Visible = new PlaidState();
plaidNewState_Visible['onVisible'] = function onVisible () {
var this$plaid=this;
var plaidNewVar81 = this$plaid.node(this$plaid.id);
var plaidNewVar80 = plaidNewVar81.removeAttribute("hidden");
return plaidNewVar80;
}
plaidNewState_Visible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Visible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Visible', ['onVisible', 'offVisible'], '']]]];
var plaidNewState_Invisible = new PlaidState();
plaidNewState_Invisible['onInvisible'] = function onInvisible () {
var this$plaid=this;
var plaidNewVar83 = this$plaid.node(this$plaid.id);
var plaidNewVar82 = plaidNewVar83.setAttribute("hidden","true");
return plaidNewVar82;
}
plaidNewState_Invisible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Invisible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Invisible', ['onInvisible', 'offVisible'], '']]]];
