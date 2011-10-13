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
var vAr199$plaid = this$plaid;
var plaidNewVar6 = this$plaid.onEmpty();
var vAr198$plaid = plaidNewVar6;
var plaidNewVar7 = function() {
var plaidNewVar8 = this$plaid.node(this$plaid.id);
var plaidNewVar9 = this$plaid.update(plaidNewVar8.value);
var vAr195$plaid = plaidNewVar9;
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
var vAr194$plaid = str;
var plaidNewVar19 = DebugWriter.write(str);
return plaidNewVar19;
};
var f = plaidNewVar7;
var plaidNewVar21 = this$plaid.node(this$plaid.id);
var plaidNewVar20 = plaidNewVar21.addEventListener("keyup",f);
var vAr197$plaid = plaidNewVar20;
var plaidNewVar23 = this$plaid.node(this$plaid.id);
var plaidNewVar22 = plaidNewVar23.addEventListener("focusout",f);
var vAr196$plaid = plaidNewVar22;
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
var plaidNewVar28 = function(vAr203$plaid) {
this$plaid.stateChange(plaidNewState_EmptyTextBox);
return this$plaid;
};
var plaidNewVar29 = function(vAr202$plaid) {
var plaidNewVar30 = function(vAr201$plaid) {
this$plaid.stateChange(plaidNewState_InvalidTextBox);
return this$plaid;
};
var plaidNewVar31 = function(vAr200$plaid) {
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
var plaidNewVar44 = function() {
return undefined;
};
plaidNewState_Button['onclick'] = plaidNewVar44;
plaidNewState_Button['init'] = function init () {
var this$plaid=this;
var plaidNewVar46 = this$plaid.node(this$plaid.id);
var plaidNewVar45 = plaidNewVar46.addEventListener("click",this$plaid.onclick);
return plaidNewVar45;
}
plaidNewState_Button.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Button', ['onclick', 'init'], '']]]];
var plaidNewState_DropDownList = new PlaidState();
plaidNewState_DropDownList['node'] = plaidNewState_UIComponent['node'];
var plaidNewVar47 = function() {
return undefined;
};
plaidNewState_DropDownList['onchange'] = plaidNewVar47;
plaidNewState_DropDownList['init'] = function init () {
var this$plaid=this;
var plaidNewVar49 = this$plaid.node(this$plaid.id);
var plaidNewVar48 = plaidNewVar49.addEventListener("change",this$plaid.onchange);
return plaidNewVar48;
}
plaidNewState_DropDownList.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['DropDownList', ['onchange', 'init'], '']]]];
var plaidNewState_Option = new PlaidState();
plaidNewState_Option['node'] = plaidNewState_UIComponent['node'];
plaidNewState_Option['init'] = function init (parent) {
var this$plaid=this;
var plaidNewVar52 = jQuery("<option />");
var plaidNewVar51 = plaidNewVar52.attr("value",this$plaid.value);
var plaidNewVar50 = plaidNewVar51.text(this$plaid.name);
var plaidNewVar54 = "#" + parent.id;
var plaidNewVar55 = jQuery(plaidNewVar54);
var plaidNewVar53 = plaidNewVar55.append(plaidNewVar50);
return plaidNewVar53;
}
plaidNewState_Option['onOption'] = function onOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_Option['offOption'] = function offOption () {
var this$plaid=this;
return undefined;
}
plaidNewState_Option.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['Option', ['init', 'onOption', 'offOption'], '']]]];
var plaidNewState_DatePicker = new PlaidState();
plaidNewState_DatePicker['node'] = plaidNewState_UIComponent['node'];
plaidNewState_DatePicker['init'] = function init () {
var this$plaid=this;
var plaidNewVar57 = "#" + this$plaid.id;
var plaidNewVar58 = jQuery(plaidNewVar57);
var plaidNewVar56 = plaidNewVar58.datepicker();
return plaidNewVar56;
}
plaidNewState_DatePicker['init_'] = function init_ () {
var this$plaid=this;
var plaidNewVar59 = function() {
this$plaid.stateChange(plaidNewState_Visible);
var vAr204$plaid = this$plaid;
var plaidNewVar60 = this$plaid.onVisible();
return plaidNewVar60;
};
var focusin = plaidNewVar59;
var plaidNewVar61 = function() {
this$plaid.stateChange(plaidNewState_Invisible);
var vAr205$plaid = this$plaid;
var plaidNewVar62 = this$plaid.onInvisible();
return plaidNewVar62;
};
var focusout = plaidNewVar61;
var plaidNewVar63 = function() {
var plaidNewVar64 = this$plaid.node(this$plaid.target);
plaidNewVar64.value = "09/30/2011";
var vAr207$plaid = plaidNewVar64.value;
this$plaid.stateChange(plaidNewState_Invisible);
var vAr206$plaid = this$plaid;
var plaidNewVar65 = this$plaid.onInvisible();
return plaidNewVar65;
};
var mouseup = plaidNewVar63;
var plaidNewVar66 = this$plaid.node(this$plaid.id);
plaidNewVar66.className = "calendar";
var vAr210$plaid = plaidNewVar66.className;
var plaidNewVar68 = this$plaid.node(this$plaid.target);
var plaidNewVar67 = plaidNewVar68.addEventListener("focusin",focusin);
var vAr209$plaid = plaidNewVar67;
var plaidNewVar70 = this$plaid.node(this$plaid.id);
var plaidNewVar69 = plaidNewVar70.addEventListener("mouseup",mouseup);
var vAr208$plaid = plaidNewVar69;
var plaidNewVar71 = this$plaid.node(this$plaid.id);
plaidNewVar71.innerText = "09/30/2011";
return plaidNewVar71.innerText;
}
plaidNewState_DatePicker.tree = [['', [], 'with'], [['UIComponent', ['node'], 'with'], [['DatePicker', ['init', 'init_'], '']]]];
var plaidNewState_Availability = new PlaidState();
plaidNewState_Availability.tree = [['', [], 'with'], [['Availability', [], 'with']]];
var plaidNewState_Enabled = new PlaidState();
plaidNewState_Enabled['onEnabled'] = function onEnabled () {
var this$plaid=this;
var plaidNewVar73 = this$plaid.node(this$plaid.id);
var plaidNewVar72 = plaidNewVar73.removeAttribute("disabled");
return plaidNewVar72;
}
plaidNewState_Enabled['offEnabled'] = function offEnabled () {
var this$plaid=this;
return undefined;
}
plaidNewState_Enabled.tree = [['', [], 'with'], [['Availability', [], 'with'], [['Enabled', ['onEnabled', 'offEnabled'], '']]]];
var plaidNewState_Disabled = new PlaidState();
plaidNewState_Disabled['onDisabled'] = function onDisabled () {
var this$plaid=this;
var plaidNewVar75 = this$plaid.node(this$plaid.id);
var plaidNewVar74 = plaidNewVar75.setAttribute("disabled","disabled");
return plaidNewVar74;
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
var plaidNewVar77 = this$plaid.node(this$plaid.id);
var plaidNewVar76 = plaidNewVar77.removeAttribute("hidden");
return plaidNewVar76;
}
plaidNewState_Visible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Visible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Visible', ['onVisible', 'offVisible'], '']]]];
var plaidNewState_Invisible = new PlaidState();
plaidNewState_Invisible['onInvisible'] = function onInvisible () {
var this$plaid=this;
var plaidNewVar79 = this$plaid.node(this$plaid.id);
var plaidNewVar78 = plaidNewVar79.setAttribute("hidden","true");
return plaidNewVar78;
}
plaidNewState_Invisible['offVisible'] = function offVisible () {
var this$plaid=this;
return undefined;
}
plaidNewState_Invisible.tree = [['', [], 'with'], [['Visibility', [], 'with'], [['Invisible', ['onInvisible', 'offVisible'], '']]]];
