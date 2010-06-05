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
	public static final java.util.List<plaid.runtime.utils.Import> vAr85$plaid;
	static {
		vAr85$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr85$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr85$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "List", toplevel = false)
	public static final plaid.runtime.PlaidObject List;
	static {
		final plaid.runtime.PlaidState vAr86$plaid;
		vAr86$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
		final plaid.runtime.PlaidObject vAr88$plaid;
		vAr88$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr89$plaid) {
				plaid.runtime.PlaidScope vAr90$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr90$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr87$plaid;
				final plaid.runtime.PlaidState vAr91$plaid;
				final plaid.runtime.PlaidObject vAr92$plaid;
				vAr92$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
				vAr91$plaid = plaid.runtime.Util.toPlaidState(vAr92$plaid);
				vAr87$plaid = vAr91$plaid.instantiate();
				return vAr87$plaid;
			}
		}
		);
		vAr86$plaid.addMember("head",vAr88$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "prepend", toplevel = false)
		final plaid.runtime.PlaidObject vAr93$plaid;
		vAr93$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject elem) {
				plaid.runtime.PlaidScope vAr95$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr95$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr94$plaid;
				final plaid.runtime.PlaidObject vAr96$plaid;
				final plaid.runtime.PlaidState vAr97$plaid;
				final plaid.runtime.PlaidState vAr98$plaid;
				final plaid.runtime.PlaidState vAr99$plaid;
				final plaid.runtime.PlaidObject vAr100$plaid;
				vAr100$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
				vAr98$plaid = plaid.runtime.Util.toPlaidState(vAr100$plaid);
				vAr99$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
				final plaid.runtime.PlaidObject vAr102$plaid;
				vAr102$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr103$plaid) {
						plaid.runtime.PlaidScope vAr104$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr104$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr101$plaid;
						vAr101$plaid = elem;
						return vAr101$plaid;
					}
				}
				);
				vAr99$plaid.addMember("value",vAr102$plaid);
				@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
				final plaid.runtime.PlaidObject vAr106$plaid;
				vAr106$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr107$plaid) {
						plaid.runtime.PlaidScope vAr108$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr108$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr105$plaid;
						vAr105$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
						return vAr105$plaid;
					}
				}
				);
				vAr99$plaid.addMember("next",vAr106$plaid);
				vAr97$plaid = vAr98$plaid.with(vAr99$plaid);
				vAr96$plaid = vAr97$plaid.instantiate();
				if (this$plaid.getMembers().containsKey("head")) this$plaid.addMember("head",vAr96$plaid);
				else throw new plaid.runtime.PlaidException("Object does not have member head.  Assignment failed.");
				vAr94$plaid = plaid.runtime.Util.unit();
				return vAr94$plaid;
			}
		}
		);
		vAr86$plaid.addMember("prepend",vAr93$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr109$plaid;
		vAr109$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject list) {
				plaid.runtime.PlaidScope vAr111$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr111$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr110$plaid;
				final plaid.runtime.PlaidObject vAr112$plaid;
				final plaid.runtime.PlaidObject vAr113$plaid;
				final plaid.runtime.PlaidObject vAr114$plaid;
				final plaid.runtime.PlaidObject vAr115$plaid;
				vAr115$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
				vAr113$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("append", vAr115$plaid);
				final plaid.runtime.PlaidObject vAr116$plaid;
				vAr116$plaid = list;
				vAr114$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", vAr116$plaid);
				vAr112$plaid = plaid.runtime.Util.call(vAr113$plaid, vAr114$plaid);
				if (this$plaid.getMembers().containsKey("head")) this$plaid.addMember("head",vAr112$plaid);
				else throw new plaid.runtime.PlaidException("Object does not have member head.  Assignment failed.");
				vAr110$plaid = plaid.runtime.Util.unit();
				return vAr110$plaid;
			}
		}
		);
		vAr86$plaid.addMember("append",vAr109$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = false)
		final plaid.runtime.PlaidObject vAr117$plaid;
		vAr117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject f) {
				plaid.runtime.PlaidScope vAr119$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr119$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr118$plaid;
				final plaid.runtime.PlaidState vAr120$plaid;
				final plaid.runtime.PlaidState vAr121$plaid;
				final plaid.runtime.PlaidState vAr122$plaid;
				final plaid.runtime.PlaidObject vAr123$plaid;
				vAr123$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", current$c0pe);
				vAr121$plaid = plaid.runtime.Util.toPlaidState(vAr123$plaid);
				vAr122$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
				final plaid.runtime.PlaidObject vAr125$plaid;
				vAr125$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr126$plaid) {
						plaid.runtime.PlaidScope vAr127$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr127$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr124$plaid;
						final plaid.runtime.PlaidObject vAr128$plaid;
						final plaid.runtime.PlaidObject vAr129$plaid;
						vAr128$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mapHelper", current$c0pe);
						final plaid.runtime.PlaidState vAr130$plaid;
						final plaid.runtime.PlaidState vAr131$plaid;
						final plaid.runtime.PlaidState vAr132$plaid;
						final plaid.runtime.PlaidObject vAr133$plaid;
						vAr133$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						final plaid.runtime.PlaidObject vAr134$plaid;
						vAr134$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr133$plaid);
						final plaid.runtime.PlaidObject vAr135$plaid;
						vAr135$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr134$plaid);
						vAr131$plaid = plaid.runtime.Util.toPlaidState(vAr135$plaid);
						vAr132$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr137$plaid;
						vAr137$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr138$plaid) {
								plaid.runtime.PlaidScope vAr139$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr139$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr136$plaid;
								vAr136$plaid = f;
								return vAr136$plaid;
							}
						}
						);
						vAr132$plaid.addMember("fst",vAr137$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr141$plaid;
						vAr141$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr142$plaid) {
								plaid.runtime.PlaidScope vAr143$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr143$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr140$plaid;
								final plaid.runtime.PlaidState vAr144$plaid;
								final plaid.runtime.PlaidState vAr145$plaid;
								final plaid.runtime.PlaidState vAr146$plaid;
								final plaid.runtime.PlaidObject vAr147$plaid;
								vAr147$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
								final plaid.runtime.PlaidObject vAr148$plaid;
								vAr148$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr147$plaid);
								final plaid.runtime.PlaidObject vAr149$plaid;
								vAr149$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr148$plaid);
								vAr145$plaid = plaid.runtime.Util.toPlaidState(vAr149$plaid);
								vAr146$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
								final plaid.runtime.PlaidObject vAr151$plaid;
								vAr151$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr152$plaid) {
										plaid.runtime.PlaidScope vAr153$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr153$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr150$plaid;
										vAr150$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("head", current$c0pe);
										return vAr150$plaid;
									}
								}
								);
								vAr146$plaid.addMember("fst",vAr151$plaid);
								@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
								final plaid.runtime.PlaidObject vAr155$plaid;
								vAr155$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr156$plaid) {
										plaid.runtime.PlaidScope vAr157$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr157$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr154$plaid;
										vAr154$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										return vAr154$plaid;
									}
								}
								);
								vAr146$plaid.addMember("snd",vAr155$plaid);
								vAr144$plaid = vAr145$plaid.with(vAr146$plaid);
								vAr140$plaid = vAr144$plaid.instantiate();
								return vAr140$plaid;
							}
						}
						);
						vAr132$plaid.addMember("snd",vAr141$plaid);
						vAr130$plaid = vAr131$plaid.with(vAr132$plaid);
						vAr129$plaid = vAr130$plaid.instantiate();
						vAr124$plaid = plaid.runtime.Util.call(vAr128$plaid, vAr129$plaid);
						return vAr124$plaid;
					}
				}
				);
				vAr122$plaid.addMember("head",vAr125$plaid);
				vAr120$plaid = vAr121$plaid.with(vAr122$plaid);
				vAr118$plaid = vAr120$plaid.instantiate();
				return vAr118$plaid;
			}
		}
		);
		vAr86$plaid.addMember("map",vAr117$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "mapHelper", toplevel = false)
		final plaid.runtime.PlaidObject vAr158$plaid;
		vAr158$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject pA1R$plaid) {
				plaid.runtime.PlaidScope vAr160$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr160$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr159$plaid;
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr161$plaid;
					final plaid.runtime.PlaidObject vAr162$plaid;
					final plaid.runtime.PlaidObject vAr163$plaid;
					vAr163$plaid = pA1R$plaid;
					vAr161$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr163$plaid);
					vAr162$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					f = plaid.runtime.Util.call(vAr161$plaid, vAr162$plaid);
					{
						final plaid.runtime.PlaidObject curr;
						final plaid.runtime.PlaidObject vAr164$plaid;
						final plaid.runtime.PlaidObject vAr165$plaid;
						final plaid.runtime.PlaidObject vAr166$plaid;
						final plaid.runtime.PlaidObject vAr167$plaid;
						final plaid.runtime.PlaidObject vAr168$plaid;
						final plaid.runtime.PlaidObject vAr169$plaid;
						vAr169$plaid = pA1R$plaid;
						vAr167$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr169$plaid);
						vAr168$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr166$plaid = plaid.runtime.Util.call(vAr167$plaid, vAr168$plaid);
						vAr164$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr166$plaid);
						vAr165$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						curr = plaid.runtime.Util.call(vAr164$plaid, vAr165$plaid);
						final plaid.runtime.PlaidObject vAr170$plaid;
						vAr170$plaid = curr;
						final plaid.runtime.PlaidState vAr171$plaid;
						final plaid.runtime.PlaidObject vAr173$plaid;
						vAr173$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
						vAr171$plaid = plaid.runtime.Util.toPlaidState(vAr173$plaid);
						final String vAr172$plaid;
						vAr172$plaid = plaid.runtime.Util.getQualifiedIdString(vAr171$plaid);
						if (vAr170$plaid.matchesTag(vAr172$plaid)) {
							{
								final plaid.runtime.PlaidObject newVal;
								final plaid.runtime.PlaidObject vAr174$plaid;
								final plaid.runtime.PlaidObject vAr175$plaid;
								vAr174$plaid = f;
								final plaid.runtime.PlaidObject vAr176$plaid;
								vAr176$plaid = curr;
								vAr175$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr176$plaid);
								newVal = plaid.runtime.Util.call(vAr174$plaid, vAr175$plaid);
								final plaid.runtime.PlaidState vAr177$plaid;
								final plaid.runtime.PlaidState vAr178$plaid;
								final plaid.runtime.PlaidState vAr179$plaid;
								final plaid.runtime.PlaidObject vAr180$plaid;
								vAr180$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
								vAr178$plaid = plaid.runtime.Util.toPlaidState(vAr180$plaid);
								vAr179$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
								final plaid.runtime.PlaidObject vAr182$plaid;
								vAr182$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr183$plaid) {
										plaid.runtime.PlaidScope vAr184$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr184$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr181$plaid;
										vAr181$plaid = newVal;
										return vAr181$plaid;
									}
								}
								);
								vAr179$plaid.addMember("value",vAr182$plaid);
								@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
								final plaid.runtime.PlaidObject vAr186$plaid;
								vAr186$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr187$plaid) {
										plaid.runtime.PlaidScope vAr188$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr188$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr185$plaid;
										final plaid.runtime.PlaidObject vAr189$plaid;
										final plaid.runtime.PlaidObject vAr190$plaid;
										vAr189$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mapHelper", current$c0pe);
										final plaid.runtime.PlaidState vAr191$plaid;
										final plaid.runtime.PlaidState vAr192$plaid;
										final plaid.runtime.PlaidState vAr193$plaid;
										final plaid.runtime.PlaidObject vAr194$plaid;
										vAr194$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
										final plaid.runtime.PlaidObject vAr195$plaid;
										vAr195$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr194$plaid);
										final plaid.runtime.PlaidObject vAr196$plaid;
										vAr196$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr195$plaid);
										vAr192$plaid = plaid.runtime.Util.toPlaidState(vAr196$plaid);
										vAr193$plaid = plaid.runtime.Util.newState();
										@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
										final plaid.runtime.PlaidObject vAr198$plaid;
										vAr198$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr199$plaid) {
												plaid.runtime.PlaidScope vAr200$plaid = current$c0pe;
												final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr200$plaid, this$plaid);
												final plaid.runtime.PlaidObject vAr197$plaid;
												vAr197$plaid = f;
												return vAr197$plaid;
											}
										}
										);
										vAr193$plaid.addMember("fst",vAr198$plaid);
										@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
										final plaid.runtime.PlaidObject vAr202$plaid;
										vAr202$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr203$plaid) {
												plaid.runtime.PlaidScope vAr204$plaid = current$c0pe;
												final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr204$plaid, this$plaid);
												final plaid.runtime.PlaidObject vAr201$plaid;
												final plaid.runtime.PlaidState vAr205$plaid;
												final plaid.runtime.PlaidState vAr206$plaid;
												final plaid.runtime.PlaidState vAr207$plaid;
												final plaid.runtime.PlaidObject vAr208$plaid;
												vAr208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
												final plaid.runtime.PlaidObject vAr209$plaid;
												vAr209$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr208$plaid);
												final plaid.runtime.PlaidObject vAr210$plaid;
												vAr210$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr209$plaid);
												vAr206$plaid = plaid.runtime.Util.toPlaidState(vAr210$plaid);
												vAr207$plaid = plaid.runtime.Util.newState();
												@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
												final plaid.runtime.PlaidObject vAr212$plaid;
												vAr212$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr213$plaid) {
														plaid.runtime.PlaidScope vAr214$plaid = current$c0pe;
														final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr214$plaid, this$plaid);
														final plaid.runtime.PlaidObject vAr211$plaid;
														final plaid.runtime.PlaidObject vAr215$plaid;
														vAr215$plaid = curr;
														vAr211$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", vAr215$plaid);
														return vAr211$plaid;
													}
												}
												);
												vAr207$plaid.addMember("fst",vAr212$plaid);
												@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
												final plaid.runtime.PlaidObject vAr217$plaid;
												vAr217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr218$plaid) {
														plaid.runtime.PlaidScope vAr219$plaid = current$c0pe;
														final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr219$plaid, this$plaid);
														final plaid.runtime.PlaidObject vAr216$plaid;
														vAr216$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
														return vAr216$plaid;
													}
												}
												);
												vAr207$plaid.addMember("snd",vAr217$plaid);
												vAr205$plaid = vAr206$plaid.with(vAr207$plaid);
												vAr201$plaid = vAr205$plaid.instantiate();
												return vAr201$plaid;
											}
										}
										);
										vAr193$plaid.addMember("snd",vAr202$plaid);
										vAr191$plaid = vAr192$plaid.with(vAr193$plaid);
										vAr190$plaid = vAr191$plaid.instantiate();
										vAr185$plaid = plaid.runtime.Util.call(vAr189$plaid, vAr190$plaid);
										return vAr185$plaid;
									}
								}
								);
								vAr179$plaid.addMember("next",vAr186$plaid);
								vAr177$plaid = vAr178$plaid.with(vAr179$plaid);
								vAr159$plaid = vAr177$plaid.instantiate();
							}
						}
						else {
							final plaid.runtime.PlaidState vAr220$plaid;
							final plaid.runtime.PlaidObject vAr222$plaid;
							vAr222$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
							vAr220$plaid = plaid.runtime.Util.toPlaidState(vAr222$plaid);
							final String vAr221$plaid;
							vAr221$plaid = plaid.runtime.Util.getQualifiedIdString(vAr220$plaid);
							if (vAr170$plaid.matchesTag(vAr221$plaid)) {
								vAr159$plaid = curr;
							}
							else {
								if (true) {
									final plaid.runtime.PlaidObject vAr223$plaid;
									final plaid.runtime.PlaidObject vAr224$plaid;
									final plaid.runtime.PlaidObject vAr225$plaid;
									final plaid.runtime.PlaidObject vAr226$plaid;
									final plaid.runtime.PlaidObject vAr227$plaid;
									final plaid.runtime.PlaidObject vAr228$plaid;
									vAr228$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", current$c0pe);
									vAr227$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr228$plaid);
									vAr226$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", vAr227$plaid);
									vAr225$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr226$plaid);
									vAr223$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr225$plaid);
									vAr224$plaid = plaid.runtime.Util.string("bad");
									vAr159$plaid = plaid.runtime.Util.call(vAr223$plaid, vAr224$plaid);
								}
								else {
									throw new RuntimeException("Pattern match exhausted.");
								}
							}
						}
					}
				}
				return vAr159$plaid;
			}
		}
		);
		vAr86$plaid.addMember("mapHelper",vAr158$plaid);
		List = vAr86$plaid.getPrototype();
	}
}
