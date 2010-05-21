/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 *
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY;
 without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License 
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */

package plaid.lang;
@plaid.runtime.annotations.RepresentsState(name = "List", toplevel = true)
public class List{
	public static final java.util.List<plaid.runtime.utils.Import> vAr153$plaid;
	static {
		vAr153$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr153$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "List", toplevel = false)
	public static final plaid.runtime.PlaidObject List;
	static {
		final plaid.runtime.PlaidState vAr154$plaid;
		vAr154$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
		final plaid.runtime.PlaidObject vAr156$plaid;
		vAr156$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr157$plaid) {
				plaid.runtime.PlaidScope vAr158$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr158$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr155$plaid;
				final plaid.runtime.PlaidState vAr159$plaid;
				final plaid.runtime.PlaidObject vAr160$plaid;
				vAr160$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
				vAr159$plaid = plaid.runtime.Util.toPlaidState(vAr160$plaid);
				vAr155$plaid = vAr159$plaid.instantiate();
				return vAr155$plaid;
			}
		}
		);
		vAr154$plaid.addMember("head",vAr156$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "prepend", toplevel = false)
		final plaid.runtime.PlaidObject vAr161$plaid;
		vAr161$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject elem) {
				plaid.runtime.PlaidScope vAr163$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr163$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr162$plaid;
				final plaid.runtime.PlaidObject vAr164$plaid;
				final plaid.runtime.PlaidState vAr165$plaid;
				final plaid.runtime.PlaidState vAr166$plaid;
				final plaid.runtime.PlaidState vAr167$plaid;
				final plaid.runtime.PlaidObject vAr168$plaid;
				vAr168$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
				vAr166$plaid = plaid.runtime.Util.toPlaidState(vAr168$plaid);
				vAr167$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
				final plaid.runtime.PlaidObject vAr170$plaid;
				vAr170$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr171$plaid) {
						plaid.runtime.PlaidScope vAr172$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr172$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr169$plaid;
						vAr169$plaid = elem;
						return vAr169$plaid;
					}
				}
				);
				vAr167$plaid.addMember("value",vAr170$plaid);
				@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
				final plaid.runtime.PlaidObject vAr174$plaid;
				vAr174$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr175$plaid) {
						plaid.runtime.PlaidScope vAr176$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr176$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr173$plaid;
						vAr173$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
						return vAr173$plaid;
					}
				}
				);
				vAr167$plaid.addMember("next",vAr174$plaid);
				vAr165$plaid = vAr166$plaid.with(vAr167$plaid);
				vAr164$plaid = vAr165$plaid.instantiate();
				if (this$plaid.getMembers().containsKey("head")) this$plaid.addMember("head",vAr164$plaid);
				else throw new plaid.runtime.PlaidException("Object does not have member head.  Assignment failed.");
				vAr162$plaid = plaid.runtime.Util.unit();
				return vAr162$plaid;
			}
		}
		);
		vAr154$plaid.addMember("prepend",vAr161$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr177$plaid;
		vAr177$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject list) {
				plaid.runtime.PlaidScope vAr179$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr179$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr178$plaid;
				final plaid.runtime.PlaidObject vAr180$plaid;
				final plaid.runtime.PlaidObject vAr181$plaid;
				final plaid.runtime.PlaidObject vAr182$plaid;
				final plaid.runtime.PlaidObject vAr183$plaid;
				vAr183$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
				vAr181$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("append", vAr183$plaid);
				final plaid.runtime.PlaidObject vAr184$plaid;
				vAr184$plaid = list;
				vAr182$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", vAr184$plaid);
				vAr180$plaid = plaid.runtime.Util.call(vAr181$plaid, vAr182$plaid);
				if (this$plaid.getMembers().containsKey("head")) this$plaid.addMember("head",vAr180$plaid);
				else throw new plaid.runtime.PlaidException("Object does not have member head.  Assignment failed.");
				vAr178$plaid = plaid.runtime.Util.unit();
				return vAr178$plaid;
			}
		}
		);
		vAr154$plaid.addMember("append",vAr177$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = false)
		final plaid.runtime.PlaidObject vAr185$plaid;
		vAr185$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject f) {
				plaid.runtime.PlaidScope vAr187$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr187$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr186$plaid;
				final plaid.runtime.PlaidState vAr188$plaid;
				final plaid.runtime.PlaidState vAr189$plaid;
				final plaid.runtime.PlaidState vAr190$plaid;
				final plaid.runtime.PlaidObject vAr191$plaid;
				vAr191$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", current$c0pe);
				vAr189$plaid = plaid.runtime.Util.toPlaidState(vAr191$plaid);
				vAr190$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
				final plaid.runtime.PlaidObject vAr193$plaid;
				vAr193$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr194$plaid) {
						plaid.runtime.PlaidScope vAr195$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr195$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr192$plaid;
						final plaid.runtime.PlaidObject vAr196$plaid;
						final plaid.runtime.PlaidObject vAr197$plaid;
						vAr196$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mapHelper", current$c0pe);
						final plaid.runtime.PlaidState vAr198$plaid;
						final plaid.runtime.PlaidState vAr199$plaid;
						final plaid.runtime.PlaidState vAr200$plaid;
						final plaid.runtime.PlaidObject vAr201$plaid;
						vAr201$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						final plaid.runtime.PlaidObject vAr202$plaid;
						vAr202$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr201$plaid);
						final plaid.runtime.PlaidObject vAr203$plaid;
						vAr203$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr202$plaid);
						vAr199$plaid = plaid.runtime.Util.toPlaidState(vAr203$plaid);
						vAr200$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr205$plaid;
						vAr205$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr206$plaid) {
								plaid.runtime.PlaidScope vAr207$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr207$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr204$plaid;
								vAr204$plaid = f;
								return vAr204$plaid;
							}
						}
						);
						vAr200$plaid.addMember("fst",vAr205$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr209$plaid;
						vAr209$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr210$plaid) {
								plaid.runtime.PlaidScope vAr211$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr211$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr208$plaid;
								final plaid.runtime.PlaidState vAr212$plaid;
								final plaid.runtime.PlaidState vAr213$plaid;
								final plaid.runtime.PlaidState vAr214$plaid;
								final plaid.runtime.PlaidObject vAr215$plaid;
								vAr215$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
								final plaid.runtime.PlaidObject vAr216$plaid;
								vAr216$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr215$plaid);
								final plaid.runtime.PlaidObject vAr217$plaid;
								vAr217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr216$plaid);
								vAr213$plaid = plaid.runtime.Util.toPlaidState(vAr217$plaid);
								vAr214$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
								final plaid.runtime.PlaidObject vAr219$plaid;
								vAr219$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr220$plaid) {
										plaid.runtime.PlaidScope vAr221$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr221$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr218$plaid;
										vAr218$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
										return vAr218$plaid;
									}
								}
								);
								vAr214$plaid.addMember("fst",vAr219$plaid);
								@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
								final plaid.runtime.PlaidObject vAr223$plaid;
								vAr223$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr224$plaid) {
										plaid.runtime.PlaidScope vAr225$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr225$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr222$plaid;
										vAr222$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										return vAr222$plaid;
									}
								}
								);
								vAr214$plaid.addMember("snd",vAr223$plaid);
								vAr212$plaid = vAr213$plaid.with(vAr214$plaid);
								vAr208$plaid = vAr212$plaid.instantiate();
								return vAr208$plaid;
							}
						}
						);
						vAr200$plaid.addMember("snd",vAr209$plaid);
						vAr198$plaid = vAr199$plaid.with(vAr200$plaid);
						vAr197$plaid = vAr198$plaid.instantiate();
						vAr192$plaid = plaid.runtime.Util.call(vAr196$plaid, vAr197$plaid);
						return vAr192$plaid;
					}
				}
				);
				vAr190$plaid.addMember("head",vAr193$plaid);
				vAr188$plaid = vAr189$plaid.with(vAr190$plaid);
				vAr186$plaid = vAr188$plaid.instantiate();
				return vAr186$plaid;
			}
		}
		);
		vAr154$plaid.addMember("map",vAr185$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "mapHelper", toplevel = false)
		final plaid.runtime.PlaidObject vAr226$plaid;
		vAr226$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject pA1R$plaid) {
				plaid.runtime.PlaidScope vAr228$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr228$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr227$plaid;
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr229$plaid;
					final plaid.runtime.PlaidObject vAr230$plaid;
					final plaid.runtime.PlaidObject vAr231$plaid;
					vAr231$plaid = pA1R$plaid;
					vAr229$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr231$plaid);
					vAr230$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					f = plaid.runtime.Util.call(vAr229$plaid, vAr230$plaid);
					{
						final plaid.runtime.PlaidObject curr;
						final plaid.runtime.PlaidObject vAr232$plaid;
						final plaid.runtime.PlaidObject vAr233$plaid;
						final plaid.runtime.PlaidObject vAr234$plaid;
						final plaid.runtime.PlaidObject vAr235$plaid;
						final plaid.runtime.PlaidObject vAr236$plaid;
						final plaid.runtime.PlaidObject vAr237$plaid;
						vAr237$plaid = pA1R$plaid;
						vAr235$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr237$plaid);
						vAr236$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr234$plaid = plaid.runtime.Util.call(vAr235$plaid, vAr236$plaid);
						vAr232$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr234$plaid);
						vAr233$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						curr = plaid.runtime.Util.call(vAr232$plaid, vAr233$plaid);
						final plaid.runtime.PlaidObject vAr238$plaid;
						vAr238$plaid = curr;
						final plaid.runtime.PlaidState vAr239$plaid;
						final plaid.runtime.PlaidObject vAr241$plaid;
						vAr241$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
						vAr239$plaid = plaid.runtime.Util.toPlaidState(vAr241$plaid);
						final String vAr240$plaid;
						vAr240$plaid = plaid.runtime.Util.getQualifiedIdString(vAr239$plaid);
						if (vAr238$plaid.matchesTag(vAr240$plaid)) {
							{
								final plaid.runtime.PlaidObject newVal;
								final plaid.runtime.PlaidObject vAr242$plaid;
								final plaid.runtime.PlaidObject vAr243$plaid;
								vAr242$plaid = f;
								final plaid.runtime.PlaidObject vAr244$plaid;
								vAr244$plaid = curr;
								vAr243$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr244$plaid);
								newVal = plaid.runtime.Util.call(vAr242$plaid, vAr243$plaid);
								final plaid.runtime.PlaidState vAr245$plaid;
								final plaid.runtime.PlaidState vAr246$plaid;
								final plaid.runtime.PlaidState vAr247$plaid;
								final plaid.runtime.PlaidObject vAr248$plaid;
								vAr248$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
								vAr246$plaid = plaid.runtime.Util.toPlaidState(vAr248$plaid);
								vAr247$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
								final plaid.runtime.PlaidObject vAr250$plaid;
								vAr250$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr251$plaid) {
										plaid.runtime.PlaidScope vAr252$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr252$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr249$plaid;
										vAr249$plaid = newVal;
										return vAr249$plaid;
									}
								}
								);
								vAr247$plaid.addMember("value",vAr250$plaid);
								@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
								final plaid.runtime.PlaidObject vAr254$plaid;
								vAr254$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr255$plaid) {
										plaid.runtime.PlaidScope vAr256$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr256$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr253$plaid;
										final plaid.runtime.PlaidObject vAr257$plaid;
										final plaid.runtime.PlaidObject vAr258$plaid;
										vAr257$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mapHelper", current$c0pe);
										final plaid.runtime.PlaidState vAr259$plaid;
										final plaid.runtime.PlaidState vAr260$plaid;
										final plaid.runtime.PlaidState vAr261$plaid;
										final plaid.runtime.PlaidObject vAr262$plaid;
										vAr262$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
										final plaid.runtime.PlaidObject vAr263$plaid;
										vAr263$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr262$plaid);
										final plaid.runtime.PlaidObject vAr264$plaid;
										vAr264$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr263$plaid);
										vAr260$plaid = plaid.runtime.Util.toPlaidState(vAr264$plaid);
										vAr261$plaid = plaid.runtime.Util.newState();
										@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
										final plaid.runtime.PlaidObject vAr266$plaid;
										vAr266$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr267$plaid) {
												plaid.runtime.PlaidScope vAr268$plaid = current$c0pe;
												final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr268$plaid, this$plaid);
												final plaid.runtime.PlaidObject vAr265$plaid;
												vAr265$plaid = f;
												return vAr265$plaid;
											}
										}
										);
										vAr261$plaid.addMember("fst",vAr266$plaid);
										@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
										final plaid.runtime.PlaidObject vAr270$plaid;
										vAr270$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr271$plaid) {
												plaid.runtime.PlaidScope vAr272$plaid = current$c0pe;
												final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr272$plaid, this$plaid);
												final plaid.runtime.PlaidObject vAr269$plaid;
												final plaid.runtime.PlaidState vAr273$plaid;
												final plaid.runtime.PlaidState vAr274$plaid;
												final plaid.runtime.PlaidState vAr275$plaid;
												final plaid.runtime.PlaidObject vAr276$plaid;
												vAr276$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
												final plaid.runtime.PlaidObject vAr277$plaid;
												vAr277$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr276$plaid);
												final plaid.runtime.PlaidObject vAr278$plaid;
												vAr278$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr277$plaid);
												vAr274$plaid = plaid.runtime.Util.toPlaidState(vAr278$plaid);
												vAr275$plaid = plaid.runtime.Util.newState();
												@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
												final plaid.runtime.PlaidObject vAr280$plaid;
												vAr280$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr281$plaid) {
														plaid.runtime.PlaidScope vAr282$plaid = current$c0pe;
														final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr282$plaid, this$plaid);
														final plaid.runtime.PlaidObject vAr279$plaid;
														final plaid.runtime.PlaidObject vAr283$plaid;
														vAr283$plaid = curr;
														vAr279$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", vAr283$plaid);
														return vAr279$plaid;
													}
												}
												);
												vAr275$plaid.addMember("fst",vAr280$plaid);
												@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
												final plaid.runtime.PlaidObject vAr285$plaid;
												vAr285$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr286$plaid) {
														plaid.runtime.PlaidScope vAr287$plaid = current$c0pe;
														final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr287$plaid, this$plaid);
														final plaid.runtime.PlaidObject vAr284$plaid;
														vAr284$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
														return vAr284$plaid;
													}
												}
												);
												vAr275$plaid.addMember("snd",vAr285$plaid);
												vAr273$plaid = vAr274$plaid.with(vAr275$plaid);
												vAr269$plaid = vAr273$plaid.instantiate();
												return vAr269$plaid;
											}
										}
										);
										vAr261$plaid.addMember("snd",vAr270$plaid);
										vAr259$plaid = vAr260$plaid.with(vAr261$plaid);
										vAr258$plaid = vAr259$plaid.instantiate();
										vAr253$plaid = plaid.runtime.Util.call(vAr257$plaid, vAr258$plaid);
										return vAr253$plaid;
									}
								}
								);
								vAr247$plaid.addMember("next",vAr254$plaid);
								vAr245$plaid = vAr246$plaid.with(vAr247$plaid);
								vAr227$plaid = vAr245$plaid.instantiate();
							}
						}
						else {
							final plaid.runtime.PlaidState vAr288$plaid;
							final plaid.runtime.PlaidObject vAr290$plaid;
							vAr290$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
							vAr288$plaid = plaid.runtime.Util.toPlaidState(vAr290$plaid);
							final String vAr289$plaid;
							vAr289$plaid = plaid.runtime.Util.getQualifiedIdString(vAr288$plaid);
							if (vAr238$plaid.matchesTag(vAr289$plaid)) {
								vAr227$plaid = curr;
							}
							else {
								if (true) {
									final plaid.runtime.PlaidObject vAr291$plaid;
									final plaid.runtime.PlaidObject vAr292$plaid;
									final plaid.runtime.PlaidObject vAr293$plaid;
									final plaid.runtime.PlaidObject vAr294$plaid;
									final plaid.runtime.PlaidObject vAr295$plaid;
									final plaid.runtime.PlaidObject vAr296$plaid;
									vAr296$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", current$c0pe);
									vAr295$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr296$plaid);
									vAr294$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", vAr295$plaid);
									vAr293$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr294$plaid);
									vAr291$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr293$plaid);
									vAr292$plaid = plaid.runtime.Util.string("bad");
									vAr227$plaid = plaid.runtime.Util.call(vAr291$plaid, vAr292$plaid);
								}
								else {
									throw new RuntimeException("Pattern match exhausted.");
								}
							}
						}
					}
				}
				return vAr227$plaid;
			}
		}
		);
		vAr154$plaid.addMember("mapHelper",vAr226$plaid);
		List = vAr154$plaid.getPrototype();
	}
}
