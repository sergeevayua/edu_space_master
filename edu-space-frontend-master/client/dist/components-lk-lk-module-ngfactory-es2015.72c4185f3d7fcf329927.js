(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{"+VzF":function(t,a,n){"use strict";n.d(a,"a",function(){return l});var d=n("lGQG"),e=n("8Y7J"),i=n("iInd");let l=(()=>{class t{constructor(t,a){this.authService=t,this.router=a}canActivate(t,a){const n=a.url;return!!this.authService.isAuthenticated()||(this.authService.setRedirectUrl(n),this.router.navigate(["login"]),!1)}}return t.\u0275prov=e["\u0275\u0275defineInjectable"]({factory:function(){return new t(e["\u0275\u0275inject"](d.a),e["\u0275\u0275inject"](i.o))},token:t,providedIn:"root"}),t})()},"1+xV":function(t,a,n){"use strict";n.r(a),n.d(a,"LkModuleNgFactory",function(){return Ht});var d=n("8Y7J");class e{}var i=n("pMnS"),l=n("YHaq"),r=n("PDjf"),o=n("omvX"),c=n("TSSN"),m=n("JUyF"),s=d["\u0275crt"]({encapsulation:2,styles:[],data:{}});function p(t){return d["\u0275vid"](0,[],null,null)}var u=n("6n/F"),h=n("Mb37"),g=n("EnSQ"),b=n("XNiG"),f=n("lGQG"),x=n("IzEk");const v={chart:{zoomType:"x",type:"column",resetZoomButton:{theme:{fill:"white",stroke:"silver"}}},tooltip:{xDateFormat:"%B %Y",nullFormat:"No data available",shared:!0,split:!1,enabled:!0,positioner:function(){return{x:40,y:-10}},style:{fontSize:"14px"},headerFormat:'<span style="font-size: 12px">{point.key}</span><br/>',shadow:!1,borderWidth:0,backgroundColor:"#eeeeee"},time:{useUTC:!0},credits:{enabled:!1},title:{text:void 0},yAxis:{allowDecimals:!1,title:{text:"\u041a\u043e\u043b-\u0432\u043e \u043a\u0443\u0440\u0441\u043e\u0432"},labels:{style:{fontSize:"14px"}},showEmpty:!0},xAxis:{type:"category",title:{text:"\u0413\u0440\u0443\u043f\u043f\u044b \u043a\u0443\u0440\u0441\u043e\u0432"},labels:{style:{fontSize:"14px"}},showEmpty:!0},series:[],colorAxis:{visible:!1},legend:{enabled:!1},plotOptions:{series:{showInLegend:!0,connectNulls:!1,marker:{enabled:!1,radius:2,states:{hover:{enabled:!0,radius:2.5}}}}},navigation:{buttonOptions:{enabled:!1}}},y=new h.a("LkComponent");class F{constructor(t,a,n,d,e,i,l){this.fb=t,this.dataService=a,this._snackBar=n,this.cdRef=d,this.authService=e,this.route=i,this.router=l,this.destroy$=new b.a,this.charts=[],this.Highcharts=u,this.chartConstructor="chart",this.updateFromInput=!1,this.optFromInput={},this.isInit=!1,this.chartCallback=t=>{this.chart=t,this.chart.showLoading(),this.dataService.getAggregatedDataForChart(1).pipe(Object(x.a)(1)).subscribe(t=>{y.debug("chart data",t);const a=t.data.map(t=>(t[1]=Number(t[1]),t));y.debug("chart data new",a),this.charts=[{name:"\u041a\u043e\u043b-\u0432\u043e \u043a\u0443\u0440\u0441\u043e\u0432",data:a}],this.drawChart(this.charts)})},Object.assign(this,{optFromInput:v})}drawChart(t){y.debug("resps",t),this.optFromInput.series=t,this.chart.hideLoading(),this.chart.redraw(!0),this.updateFromInput=!0,this.cdRef.markForCheck()}ngOnInit(){}ngOnDestroy(){this.destroy$.next(!0),this.destroy$.unsubscribe(),this.optFromInput.series=[],this.optFromInput.annotations=[]}}var w=n("s7LF"),k=n("zHaW"),C=n("iInd"),I=d["\u0275crt"]({encapsulation:0,styles:[[".highcharts-container{width:100%!important}  highcharts-chart{width:100%!important;height:354px!important;display:block}"]],data:{}});function _(t){return d["\u0275vid"](0,[(t()(),d["\u0275eld"](0,0,null,null,13,"div",[["class","padding"]],null,null,null,null,null)),(t()(),d["\u0275eld"](1,0,null,null,12,"mat-card",[["class","full-width mat-card mat-focus-indicator"]],[[2,"_mat-animation-noopable",null]],null,null,l.d,l.a)),d["\u0275did"](2,49152,null,0,r.a,[[2,o.a]],null,null),(t()(),d["\u0275eld"](3,0,null,0,10,"mat-card-content",[["class","mat-card-content"]],null,null,null,null,null)),d["\u0275did"](4,16384,null,0,r.b,[],null,null),(t()(),d["\u0275eld"](5,0,null,null,8,"div",[],null,null,null,null,null)),(t()(),d["\u0275eld"](6,0,null,null,4,"div",[["class","title-flex-status"]],null,null,null,null,null)),(t()(),d["\u0275eld"](7,0,null,null,3,"div",[["class","title"]],null,null,null,null,null)),(t()(),d["\u0275eld"](8,0,null,null,2,"h3",[["class","nm"]],null,null,null,null,null)),(t()(),d["\u0275ted"](9,null,["",""])),d["\u0275pid"](131072,c.j,[c.k,d.ChangeDetectorRef]),(t()(),d["\u0275eld"](11,0,null,null,2,"div",[["class","padding npb"]],null,null,null,null,null)),(t()(),d["\u0275eld"](12,0,null,null,1,"highcharts-chart",[["id","container"]],null,[[null,"updateChange"]],function(t,a,n){var d=!0;return"updateChange"===a&&(d=!1!==(t.component.updateFromInput=n)&&d),d},p,s)),d["\u0275did"](13,704512,null,0,m.a,[d.ElementRef,d.NgZone],{Highcharts:[0,"Highcharts"],constructorType:[1,"constructorType"],callbackFunction:[2,"callbackFunction"],oneToOne:[3,"oneToOne"],options:[4,"options"],update:[5,"update"]},{updateChange:"updateChange"})],function(t,a){var n=a.component;t(a,13,0,n.Highcharts,n.chartConstructor,n.chartCallback,!0,n.optFromInput,n.updateFromInput)},function(t,a){t(a,1,0,"NoopAnimations"===d["\u0275nov"](a,2)._animationMode),t(a,9,0,d["\u0275unv"](a,9,0,d["\u0275nov"](a,10).transform("\u0413\u0440\u0430\u0444\u0438\u043a \u043a\u043e\u043b-\u0432\u0430 \u043a\u0443\u0440\u0441\u043e\u0432 \u043f\u043e \u0433\u0440\u0443\u043f\u043f\u0430\u043c")))})}function z(t){return d["\u0275vid"](0,[(t()(),d["\u0275eld"](0,0,null,null,1,"app-lk",[],null,null,null,_,I)),d["\u0275did"](1,245760,null,0,F,[w.FormBuilder,g.a,k.b,d.ChangeDetectorRef,f.a,C.a,C.o],null,null)],function(t,a){t(a,1,0)},null)}var j=d["\u0275ccf"]("app-lk",F,z,{},{},[]),R=n("Zfjg"),S=n("9cE2"),L=n("nmIE"),M=n("ntJQ"),N=n("007U"),A=n("No7X"),E=n("bIR2"),T=n("SVse"),O=n("1O3W"),U=n("9gLZ"),Y=n("vrAh"),D=n("9b/N"),Q=n("UhP/"),Z=n("hzfI"),q=n("iELJ"),J=n("TN/R"),H=n("rJgo"),X=n("SCoL"),G=n("ZTz/"),K=n("ZFy/"),P=n("5QHs"),V=n("LUZP"),B=n("Tq4R"),W=n("rAFq"),$=n("4D9t"),tt=n("bMPK"),at=n("UiI2");class nt{}var dt=n("1z/I"),et=n("7KAL"),it=n("YEUz"),lt=n("8Qe2"),rt=n("Km1n"),ot=n("Dxy4"),ct=n("Ynp+"),mt=n("pMoy"),st=n("f44v"),pt=n("q59W"),ut=n("Tj54"),ht=n("BSbQ"),gt=n("GF+f"),bt=n("o4Yh"),ft=n("40+f"),xt=n("8sFK"),vt=n("Q2Ze"),yt=n("e6WT"),Ft=n("SqCe"),wt=n("BTe0"),kt=n("pu8Q"),Ct=n("zQhy"),It=n("q7Ft"),_t=n("mPVK"),zt=n("jMqV"),jt=n("GXRp"),Rt=n("OaSA"),St=n("M9ds"),Lt=n("l0rg"),Mt=n("y7ui"),Nt=n("OLiY"),At=n("vXR4"),Et=n("jRYl"),Tt=n("KL2N"),Ot=n("QX+E"),Ut=n("9eZp"),Yt=n("JdLL"),Dt=n("wdhI"),Qt=n("FpXt"),Zt=n("+VzF"),qt=n("Ht+U"),Jt=n("EFU/"),Ht=d["\u0275cmf"](e,[],function(t){return d["\u0275mod"]([d["\u0275mpd"](512,d.ComponentFactoryResolver,d["\u0275CodegenComponentFactoryResolver"],[[8,[i.a,j,R.a,S.a,L.b,L.a,M.a,N.a,N.b,A.a,E.a]],[3,d.ComponentFactoryResolver],d.NgModuleRef]),d["\u0275mpd"](4608,T.n,T.m,[d.LOCALE_ID]),d["\u0275mpd"](4608,O.d,O.d,[O.k,O.f,d.ComponentFactoryResolver,O.j,O.g,d.Injector,d.NgZone,T.d,U.b,T.h,O.i]),d["\u0275mpd"](5120,O.l,O.m,[O.d]),d["\u0275mpd"](5120,Y.a,Y.b,[O.d]),d["\u0275mpd"](4608,D.c,D.c,[]),d["\u0275mpd"](4608,Q.d,Q.d,[]),d["\u0275mpd"](5120,Z.b,Z.a,[[3,Z.b]]),d["\u0275mpd"](5120,q.b,q.c,[O.d]),d["\u0275mpd"](135680,q.d,q.d,[O.d,d.Injector,[2,T.h],[2,q.a],q.b,[3,q.d],O.f]),d["\u0275mpd"](4608,J.o,J.o,[]),d["\u0275mpd"](5120,J.a,J.b,[O.d]),d["\u0275mpd"](5120,H.c,H.i,[O.d]),d["\u0275mpd"](4608,Q.c,Q.w,[[2,Q.g],X.a]),d["\u0275mpd"](5120,G.b,G.c,[O.d]),d["\u0275mpd"](5120,K.a,K.b,[O.d]),d["\u0275mpd"](5120,P.b,P.a,[[3,P.b]]),d["\u0275mpd"](5120,V.b,V.a,[[3,V.b]]),d["\u0275mpd"](5120,B.b,B.d,[O.d]),d["\u0275mpd"](4608,B.e,B.e,[O.d,d.Injector,[2,T.h],B.b,[2,B.a],[3,B.e],O.f]),d["\u0275mpd"](4608,W.a,W.a,[]),d["\u0275mpd"](5120,$.a,$.c,[O.d]),d["\u0275mpd"](4608,tt.a,at.a,[[2,tt.b],X.a]),d["\u0275mpd"](1073742336,T.c,T.c,[]),d["\u0275mpd"](1073742336,C.s,C.s,[[2,C.y],[2,C.o]]),d["\u0275mpd"](1073742336,nt,nt,[]),d["\u0275mpd"](1073742336,c.h,c.h,[]),d["\u0275mpd"](1073742336,U.a,U.a,[]),d["\u0275mpd"](1073742336,dt.g,dt.g,[]),d["\u0275mpd"](1073742336,X.b,X.b,[]),d["\u0275mpd"](1073742336,et.b,et.b,[]),d["\u0275mpd"](1073742336,et.d,et.d,[]),d["\u0275mpd"](1073742336,O.h,O.h,[]),d["\u0275mpd"](1073742336,Q.l,Q.l,[it.i,[2,Q.e],T.d]),d["\u0275mpd"](1073742336,Q.v,Q.v,[]),d["\u0275mpd"](1073742336,Q.t,Q.t,[]),d["\u0275mpd"](1073742336,Q.q,Q.q,[]),d["\u0275mpd"](1073742336,Y.c,Y.c,[]),d["\u0275mpd"](1073742336,D.d,D.d,[]),d["\u0275mpd"](1073742336,it.a,it.a,[it.i]),d["\u0275mpd"](1073742336,lt.a,lt.a,[]),d["\u0275mpd"](1073742336,rt.c,rt.c,[]),d["\u0275mpd"](1073742336,ot.c,ot.c,[]),d["\u0275mpd"](1073742336,ct.a,ct.a,[]),d["\u0275mpd"](1073742336,r.d,r.d,[]),d["\u0275mpd"](1073742336,mt.b,mt.b,[]),d["\u0275mpd"](1073742336,mt.a,mt.a,[]),d["\u0275mpd"](1073742336,st.b,st.b,[]),d["\u0275mpd"](1073742336,pt.e,pt.e,[]),d["\u0275mpd"](1073742336,ut.c,ut.c,[]),d["\u0275mpd"](1073742336,Z.c,Z.c,[]),d["\u0275mpd"](1073742336,q.i,q.i,[]),d["\u0275mpd"](1073742336,J.p,J.p,[]),d["\u0275mpd"](1073742336,ht.a,ht.a,[]),d["\u0275mpd"](1073742336,gt.c,gt.c,[]),d["\u0275mpd"](1073742336,bt.a,bt.a,[]),d["\u0275mpd"](1073742336,Q.m,Q.m,[]),d["\u0275mpd"](1073742336,ft.a,ft.a,[]),d["\u0275mpd"](1073742336,xt.c,xt.c,[]),d["\u0275mpd"](1073742336,vt.g,vt.g,[]),d["\u0275mpd"](1073742336,yt.b,yt.b,[]),d["\u0275mpd"](1073742336,Ft.a,Ft.a,[]),d["\u0275mpd"](1073742336,H.h,H.h,[]),d["\u0275mpd"](1073742336,H.f,H.f,[]),d["\u0275mpd"](1073742336,Q.x,Q.x,[]),d["\u0275mpd"](1073742336,Q.n,Q.n,[]),d["\u0275mpd"](1073742336,G.e,G.e,[]),d["\u0275mpd"](1073742336,K.c,K.c,[]),d["\u0275mpd"](1073742336,P.c,P.c,[]),d["\u0275mpd"](1073742336,wt.a,wt.a,[]),d["\u0275mpd"](1073742336,kt.a,kt.a,[]),d["\u0275mpd"](1073742336,Ct.a,Ct.a,[]),d["\u0275mpd"](1073742336,It.h,It.h,[]),d["\u0275mpd"](1073742336,_t.a,_t.a,[]),d["\u0275mpd"](1073742336,zt.b,zt.b,[]),d["\u0275mpd"](1073742336,zt.a,zt.a,[]),d["\u0275mpd"](1073742336,k.e,k.e,[]),d["\u0275mpd"](1073742336,V.c,V.c,[]),d["\u0275mpd"](1073742336,jt.p,jt.p,[]),d["\u0275mpd"](1073742336,Rt.a,Rt.a,[]),d["\u0275mpd"](1073742336,St.l,St.l,[]),d["\u0275mpd"](1073742336,Lt.b,Lt.b,[]),d["\u0275mpd"](1073742336,Mt.d,Mt.d,[]),d["\u0275mpd"](1073742336,Nt.c,Nt.c,[]),d["\u0275mpd"](1073742336,w["\u0275angular_packages_forms_forms_o"],w["\u0275angular_packages_forms_forms_o"],[]),d["\u0275mpd"](1073742336,w["\u0275angular_packages_forms_forms_d"],w["\u0275angular_packages_forms_forms_d"],[]),d["\u0275mpd"](1073742336,w.ReactiveFormsModule,w.ReactiveFormsModule,[]),d["\u0275mpd"](1073742336,At.a,At.a,[]),d["\u0275mpd"](1073742336,w.FormsModule,w.FormsModule,[]),d["\u0275mpd"](1073742336,m.b,m.b,[]),d["\u0275mpd"](1073742336,Et.a,Et.a,[]),d["\u0275mpd"](1073742336,Tt.a,Tt.a,[]),d["\u0275mpd"](1073742336,Ot.a,Ot.a,[]),d["\u0275mpd"](1073742336,Ot.b,Ot.b,[]),d["\u0275mpd"](1073742336,Ut.b,Ut.b,[]),d["\u0275mpd"](1073742336,Yt.CustomFormsModule,Yt.CustomFormsModule,[]),d["\u0275mpd"](1073742336,Dt.a,Dt.a,[]),d["\u0275mpd"](1073742336,Qt.a,Qt.a,[]),d["\u0275mpd"](1073742336,e,e,[]),d["\u0275mpd"](1024,C.m,function(){return[[{path:"",component:F,canActivate:[Zt.a]}]]},[]),d["\u0275mpd"](256,st.a,{separatorKeyCodes:[qt.f]},[]),d["\u0275mpd"](256,Q.f,Q.h,[]),d["\u0275mpd"](256,Jt.a,Ot.c,[])])})},YHaq:function(t,a,n){"use strict";n.d(a,"a",function(){return e}),n.d(a,"d",function(){return i}),n.d(a,"b",function(){return l}),n.d(a,"c",function(){return r});var d=n("8Y7J"),e=(n("PDjf"),n("9gLZ"),n("UhP/"),n("YEUz"),n("SVse"),n("omvX"),d["\u0275crt"]({encapsulation:2,styles:[".mat-card{transition:box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);display:block;position:relative;padding:16px;border-radius:4px}._mat-animation-noopable.mat-card{transition:none;animation:none}.mat-card .mat-divider-horizontal{position:absolute;left:0;width:100%}[dir=rtl] .mat-card .mat-divider-horizontal{left:auto;right:0}.mat-card .mat-divider-horizontal.mat-divider-inset{position:static;margin:0}[dir=rtl] .mat-card .mat-divider-horizontal.mat-divider-inset{margin-right:0}.cdk-high-contrast-active .mat-card{outline:solid 1px}.mat-card-actions,.mat-card-subtitle,.mat-card-content{display:block;margin-bottom:16px}.mat-card-title{display:block;margin-bottom:8px}.mat-card-actions{margin-left:-8px;margin-right:-8px;padding:8px 0}.mat-card-actions-align-end{display:flex;justify-content:flex-end}.mat-card-image{width:calc(100% + 32px);margin:0 -16px 16px -16px}.mat-card-footer{display:block;margin:0 -16px -16px -16px}.mat-card-actions .mat-button,.mat-card-actions .mat-raised-button,.mat-card-actions .mat-stroked-button{margin:0 8px}.mat-card-header{display:flex;flex-direction:row}.mat-card-header .mat-card-title{margin-bottom:12px}.mat-card-header-text{margin:0 16px}.mat-card-avatar{height:40px;width:40px;border-radius:50%;flex-shrink:0;object-fit:cover}.mat-card-title-group{display:flex;justify-content:space-between}.mat-card-sm-image{width:80px;height:80px}.mat-card-md-image{width:112px;height:112px}.mat-card-lg-image{width:152px;height:152px}.mat-card-xl-image{width:240px;height:240px;margin:-8px}.mat-card-title-group>.mat-card-xl-image{margin:-8px 0 8px}@media(max-width: 599px){.mat-card-title-group{margin:0}.mat-card-xl-image{margin-left:0;margin-right:0}}.mat-card>:first-child,.mat-card-content>:first-child{margin-top:0}.mat-card>:last-child:not(.mat-card-footer),.mat-card-content>:last-child:not(.mat-card-footer){margin-bottom:0}.mat-card-image:first-child{margin-top:-16px;border-top-left-radius:inherit;border-top-right-radius:inherit}.mat-card>.mat-card-actions:last-child{margin-bottom:-8px;padding-bottom:0}.mat-card-actions:not(.mat-card-actions-align-end) .mat-button:first-child,.mat-card-actions:not(.mat-card-actions-align-end) .mat-raised-button:first-child,.mat-card-actions:not(.mat-card-actions-align-end) .mat-stroked-button:first-child{margin-left:0;margin-right:0}.mat-card-actions-align-end .mat-button:last-child,.mat-card-actions-align-end .mat-raised-button:last-child,.mat-card-actions-align-end .mat-stroked-button:last-child{margin-left:0;margin-right:0}.mat-card-title:not(:first-child),.mat-card-subtitle:not(:first-child){margin-top:-4px}.mat-card-header .mat-card-subtitle:not(:first-child){margin-top:-8px}.mat-card>.mat-card-xl-image:first-child{margin-top:-8px}.mat-card>.mat-card-xl-image:last-child{margin-bottom:-8px}\n"],data:{}}));function i(t){return d["\u0275vid"](2,[d["\u0275ncd"](null,0),d["\u0275ncd"](null,1)],null,null)}var l=d["\u0275crt"]({encapsulation:2,styles:[],data:{}});function r(t){return d["\u0275vid"](2,[d["\u0275ncd"](null,0),(t()(),d["\u0275eld"](1,0,null,null,1,"div",[["class","mat-card-header-text"]],null,null,null,null,null)),d["\u0275ncd"](null,1),d["\u0275ncd"](null,2)],null,null)}}}]);