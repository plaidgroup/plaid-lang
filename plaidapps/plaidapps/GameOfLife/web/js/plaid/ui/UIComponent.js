var plaidNewState_UIComponent = new PlaidState();
var plaidNewVar1 = function(id) {
var plaidNewVar2 = document.getElementById(id);
return plaidNewVar2;
};
plaidNewState_UIComponent['node'] = plaidNewVar1;
var plaidNewVar3 = function(id) {
var plaidNewVar4 = "#" + id;
var plaidNewVar5 = jQuery(plaidNewVar4);
return plaidNewVar5;
};
plaidNewState_UIComponent['jqnode'] = plaidNewVar3;
plaidNewState_UIComponent.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with']]];
var plaidNewState_Box = new PlaidState();
plaidNewState_Box['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Box['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_Box.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Box', [], '']]]];
var plaidNewState_Accordion = new PlaidState();
plaidNewState_Accordion['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Accordion['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_Accordion['init'] = function init () {
var this$plaid=this;
var plaidNewVar7 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar6 = plaidNewVar7.accordion();
return plaidNewVar6;
}
plaidNewState_Accordion.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Accordion', ['init'], '']]]];
var plaidNewState_TextBox = new PlaidState();
plaidNewState_TextBox['node'] = plaidNewState_UIComponent['node'];
plaidNewState_TextBox['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_TextBox['init'] = function init () {
var this$plaid=this;
this$plaid.stateChange(plaidNewState_EmptyTextBox);
var vAr209$plaid = this$plaid;
var plaidNewVar8 = this$plaid.onEmpty();
var vAr208$plaid = plaidNewVar8;
var plaidNewVar9 = function() {
var plaidNewVar10 = this$plaid.node(this$plaid.id);
var plaidNewVar11 = this$plaid.update(plaidNewVar10.value);
return plaidNewVar11;
};
var f = plaidNewVar9;
var plaidNewVar13 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar12 = plaidNewVar13.bind("keyup",f);
var vAr207$plaid = plaidNewVar12;
var plaidNewVar15 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar14 = plaidNewVar15.bind("focusout",f);
var vAr206$plaid = plaidNewVar14;
var plaidNewVar16 = window.setInterval(f,100);
return plaidNewVar16;
}
plaidNewState_TextBox['update'] = function update (text) {
var this$plaid=this;
var plaidNewVar17 = function(vAr213$plaid) {
this$plaid.stateChange(plaidNewState_EmptyTextBox);
return this$plaid;
};
var plaidNewVar18 = function(vAr212$plaid) {
var plaidNewVar19 = function(vAr211$plaid) {
this$plaid.stateChange(plaidNewState_InvalidTextBox);
return this$plaid;
};
var plaidNewVar20 = function(vAr210$plaid) {
this$plaid.stateChange(plaidNewState_ValidTextBox);
return this$plaid;
};
var plaidNewVar21 = text.length <= this$plaid.max;
var plaidNewVar23 = this$plaid.min <= text.length;
var plaidNewVar22 = plaidNewVar23 && plaidNewVar21;
var plaidNewVar24 = ifElse(plaidNewVar22);
var plaidNewVar25 = plaidNewVar24(plaidNewVar20);
var plaidNewVar26 = plaidNewVar25(plaidNewVar19);
return plaidNewVar26;
};
var plaidNewVar27 = text.length > 0;
var plaidNewVar28 = ifElse(plaidNewVar27);
var plaidNewVar29 = plaidNewVar28(plaidNewVar18);
var plaidNewVar30 = plaidNewVar29(plaidNewVar17);
return plaidNewVar30;
}
plaidNewState_TextBox.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['TextBox', ['init', 'update'], '']]]];
var plaidNewState_EmptyTextBox = new PlaidState();
plaidNewState_EmptyTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_EmptyTextBox['jqnode'] = plaidNewState_TextBox['jqnode'];
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
plaidNewState_EmptyTextBox.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['TextBox', ['init', 'update'], ''], [['EmptyTextBox', ['onEmpty', 'offEmpty'], '']]]]];
var plaidNewState_InvalidTextBox = new PlaidState();
plaidNewState_InvalidTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_InvalidTextBox['jqnode'] = plaidNewState_TextBox['jqnode'];
plaidNewState_InvalidTextBox['init'] = plaidNewState_TextBox['init'];
plaidNewState_InvalidTextBox['update'] = plaidNewState_TextBox['update'];
plaidNewState_InvalidTextBox['validate'] = function validate () {
var this$plaid=this;
var plaidNewVar31 = returnFalse();
return plaidNewVar31;
}
plaidNewState_InvalidTextBox['onInvalid'] = function onInvalid () {
var this$plaid=this;
return undefined;
}
plaidNewState_InvalidTextBox['offInvalid'] = function offInvalid () {
var this$plaid=this;
return undefined;
}
plaidNewState_InvalidTextBox.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['TextBox', ['init', 'update'], ''], [['InvalidTextBox', ['validate', 'onInvalid', 'offInvalid'], '']]]]];
var plaidNewState_ValidTextBox = new PlaidState();
plaidNewState_ValidTextBox['node'] = plaidNewState_TextBox['node'];
plaidNewState_ValidTextBox['jqnode'] = plaidNewState_TextBox['jqnode'];
plaidNewState_ValidTextBox['init'] = plaidNewState_TextBox['init'];
plaidNewState_ValidTextBox['update'] = plaidNewState_TextBox['update'];
plaidNewState_ValidTextBox['validate'] = function validate () {
var this$plaid=this;
var plaidNewVar32 = returnTrue();
return plaidNewVar32;
}
plaidNewState_ValidTextBox['onValid'] = function onValid () {
var this$plaid=this;
return undefined;
}
plaidNewState_ValidTextBox['offValid'] = function offValid () {
var this$plaid=this;
return undefined;
}
plaidNewState_ValidTextBox.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['TextBox', ['init', 'update'], ''], [['ValidTextBox', ['validate', 'onValid', 'offValid'], '']]]]];
var plaidNewState_Button = new PlaidState();
plaidNewState_Button['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Button['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_Button['init'] = function init () {
var this$plaid=this;
var plaidNewVar33 = function() {
var plaidNewVar34 = this$plaid.mousedown();
return plaidNewVar34;
};
var plaidNewVar36 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar35 = plaidNewVar36.bind("mousedown",plaidNewVar33);
var vAr215$plaid = plaidNewVar35;
var plaidNewVar37 = function() {
var plaidNewVar38 = this$plaid.mouseup();
return plaidNewVar38;
};
var plaidNewVar40 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar39 = plaidNewVar40.bind("mouseup",plaidNewVar37);
var vAr214$plaid = plaidNewVar39;
var plaidNewVar41 = function() {
var plaidNewVar42 = this$plaid.mouseout();
return plaidNewVar42;
};
var plaidNewVar44 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar43 = plaidNewVar44.bind("mouseout",plaidNewVar41);
return plaidNewVar43;
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
var plaidNewVar45 = this$plaid.click();
var vAr216$plaid = plaidNewVar45;
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
plaidNewState_Button.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], '']]]];
var plaidNewState_PressedButton = new PlaidState();
plaidNewState_PressedButton['node'] = plaidNewState_Button['node'];
plaidNewState_PressedButton['jqnode'] = plaidNewState_Button['jqnode'];
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
plaidNewState_PressedButton.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], ''], [['PressedButton', ['onPressedButton', 'offPressedButton'], '']]]]];
var plaidNewState_UnpressedButton = new PlaidState();
plaidNewState_UnpressedButton['node'] = plaidNewState_Button['node'];
plaidNewState_UnpressedButton['jqnode'] = plaidNewState_Button['jqnode'];
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
plaidNewState_UnpressedButton.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Button', ['init', 'mousedown', 'mouseup', 'mouseout'], ''], [['UnpressedButton', ['onUnpressedButton', 'offUnpressedButton'], '']]]]];
var plaidNewState_DropDownList = new PlaidState();
plaidNewState_DropDownList['node'] = plaidNewState_UIComponent['node'];
plaidNewState_DropDownList['jqnode'] = plaidNewState_UIComponent['jqnode'];
var plaidNewVar46 = returnArray();
plaidNewState_DropDownList['options'] = plaidNewVar46;
var plaidNewVar47 = function() {
return undefined;
};
plaidNewState_DropDownList['beforeChange'] = plaidNewVar47;
var plaidNewVar48 = function() {
return undefined;
};
plaidNewState_DropDownList['afterChange'] = plaidNewVar48;
plaidNewState_DropDownList['init'] = function init () {
var this$plaid=this;
var plaidNewVar49 = function(evt) {
var plaidNewVar50 = this$plaid.beforeChange();
var vAr220$plaid = plaidNewVar50;
var v = evt.target.value;
var plaidNewVar51 = function(o) {
var matchVar = o;
if (matchVar.match("SelectedOption")) {
o.stateChange(plaidNewState_UnselectedOption);
var returnVal = o;
}
var vAr218$plaid = returnVal;
var plaidNewVar52 = function(vAr217$plaid) {
o.stateChange(plaidNewState_SelectedOption);
return o;
};
var plaidNewVar53 = o.value == v;
var plaidNewVar54 = if$plaid(plaidNewVar53);
var plaidNewVar55 = plaidNewVar54(plaidNewVar52);
return plaidNewVar55;
};
var plaidNewVar56 = this$plaid.options.each(plaidNewVar51);
var vAr219$plaid = plaidNewVar56;
var plaidNewVar57 = this$plaid.afterChange();
return plaidNewVar57;
};
var plaidNewVar59 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar58 = plaidNewVar59.bind("change",plaidNewVar49);
return plaidNewVar58;
}
plaidNewState_DropDownList['addOption'] = function addOption (option) {
var this$plaid=this;
var plaidNewVar60 = this$plaid.options.push(option);
return plaidNewVar60;
}
plaidNewState_DropDownList.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['DropDownList', ['options', 'beforeChange', 'afterChange', 'init', 'addOption'], '']]]];
var plaidNewState_Option = new PlaidState();
plaidNewState_Option['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Option['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_Option['init'] = function init (parent) {
var this$plaid=this;
var plaidNewVar61 = parent.addOption(this$plaid);
return plaidNewVar61;
}
plaidNewState_Option.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Option', ['init'], '']]]];
var plaidNewState_SelectedOption = new PlaidState();
plaidNewState_SelectedOption['node'] = plaidNewState_Option['node'];
plaidNewState_SelectedOption['jqnode'] = plaidNewState_Option['jqnode'];
plaidNewState_SelectedOption['init'] = plaidNewState_Option['init'];
plaidNewState_SelectedOption['onSelectedOption'] = function onSelectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_SelectedOption['offSelectedOption'] = function offSelectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_SelectedOption.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Option', ['init'], ''], [['SelectedOption', ['onSelectedOption', 'offSelectedOption'], '']]]]];
var plaidNewState_UnselectedOption = new PlaidState();
plaidNewState_UnselectedOption['node'] = plaidNewState_Option['node'];
plaidNewState_UnselectedOption['jqnode'] = plaidNewState_Option['jqnode'];
plaidNewState_UnselectedOption['init'] = plaidNewState_Option['init'];
plaidNewState_UnselectedOption['onUnselectedOption'] = function onUnselectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnselectedOption['offUnselectedOption'] = function offUnselectedOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_UnselectedOption.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['Option', ['init'], ''], [['UnselectedOption', ['onUnselectedOption', 'offUnselectedOption'], '']]]]];
var plaidNewState_DatePicker = new PlaidState();
plaidNewState_DatePicker['node'] = plaidNewState_UIComponent['node'];
plaidNewState_DatePicker['jqnode'] = plaidNewState_UIComponent['jqnode'];
plaidNewState_DatePicker['init'] = function init () {
var this$plaid=this;
var plaidNewVar63 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar62 = plaidNewVar63.datepicker();
return plaidNewVar62;
}
plaidNewState_DatePicker.tree = [['', [], 'with'], [['UIComponent', ['node', 'jqnode'], 'with'], [['DatePicker', ['init'], '']]]];
var plaidNewState_Availability = new PlaidState();
plaidNewState_Availability.tree = [['', [], 'with'], [['Availability', [], 'with']]];
var plaidNewState_Enabled = new PlaidState();
plaidNewState_Enabled['onEnabled'] = function onEnabled () {
var this$plaid=this;
var plaidNewVar65 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar64 = plaidNewVar65.removeAttr("disabled");
return plaidNewVar64;
}
plaidNewState_Enabled['offEnabled'] = function offEnabled () {
var this$plaid=this;
return undefined;
}
plaidNewState_Enabled.tree = [['', [], 'with'], [['Availability', [], 'with'], [['Enabled', ['onEnabled', 'offEnabled'], '']]]];
var plaidNewState_Disabled = new PlaidState();
plaidNewState_Disabled['onDisabled'] = function onDisabled () {
var this$plaid=this;
var plaidNewVar67 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar66 = plaidNewVar67.attr("disabled","disabled");
return plaidNewVar66;
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
var plaidNewVar69 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar68 = plaidNewVar69.show();
return plaidNewVar68;
}
plaidNewState_Visible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Visible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Visible', ['onVisible', 'offVisible'], '']]]];
var plaidNewState_Invisible = new PlaidState();
plaidNewState_Invisible['onInvisible'] = function onInvisible () {
var this$plaid=this;
var plaidNewVar71 = this$plaid.jqnode(this$plaid.id);
var plaidNewVar70 = plaidNewVar71.hide();
return plaidNewVar70;
}
plaidNewState_Invisible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Invisible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Invisible', ['onInvisible', 'offVisible'], '']]]];
