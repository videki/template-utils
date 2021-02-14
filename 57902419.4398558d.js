(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{75:function(e,t,r){"use strict";r.r(t),r.d(t,"frontMatter",(function(){return i})),r.d(t,"metadata",(function(){return c})),r.d(t,"toc",(function(){return s})),r.d(t,"default",(function(){return u}));var n=r(3),o=r(7),a=(r(0),r(90)),i={id:"roadmap",title:"Roadmap"},c={unversionedId:"about/roadmap",id:"about/roadmap",isDocsHomePage:!1,title:"Roadmap",description:"We would like to add more features to the functionality to let teams using this tool to focus more on business needs and",source:"@site/docs/about/roadmap.md",slug:"/about/roadmap",permalink:"/template-utils/docs/about/roadmap",editUrl:"https://github.com/videki/template-utils/docs/about/roadmap.md",version:"current",sidebar:"docs",previous:{title:"Customization examples",permalink:"/template-utils/docs/samples/customization-examples"}},s=[],l={toc:s};function u(e){var t=e.components,r=Object(o.a)(e,["components"]);return Object(a.b)("wrapper",Object(n.a)({},l,r,{components:t,mdxType:"MDXLayout"}),Object(a.b)("p",null,"We would like to add more features to the functionality to let teams using this tool to focus more on business needs and\nprovide open source DMS functionality. To achieve this we are likely to add features like listed below:   "),Object(a.b)("ul",null,Object(a.b)("li",{parentName:"ul"},"VCS based template repository: git repository based template store to provide template and document structure history"),Object(a.b)("li",{parentName:"ul"},"Result store database support: ",Object(a.b)("ul",{parentName:"li"},Object(a.b)("li",{parentName:"ul"},"download cache (redis)"),Object(a.b)("li",{parentName:"ul"},"long-term store (git)    "))),Object(a.b)("li",{parentName:"ul"},"Document set decision table support: rule based decision table control for template alternatives to\nprovide business editable template sets (Excel based Drools decision table)"),Object(a.b)("li",{parentName:"ul"},"DTO jsonpath and microservice support (to run the tool as a separate service without any impact of the DTO changes\nused in then templates)  "),Object(a.b)("li",{parentName:"ul"},"Document signing  ")),Object(a.b)("p",null,"(Note: These are improvement ideas of the core team, not scheduled tasks with deadlines)"))}u.isMDXComponent=!0},90:function(e,t,r){"use strict";r.d(t,"a",(function(){return p})),r.d(t,"b",(function(){return b}));var n=r(0),o=r.n(n);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function i(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function c(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?i(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function s(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var l=o.a.createContext({}),u=function(e){var t=o.a.useContext(l),r=t;return e&&(r="function"==typeof e?e(t):c(c({},t),e)),r},p=function(e){var t=u(e.components);return o.a.createElement(l.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},m=o.a.forwardRef((function(e,t){var r=e.components,n=e.mdxType,a=e.originalType,i=e.parentName,l=s(e,["components","mdxType","originalType","parentName"]),p=u(r),m=n,b=p["".concat(i,".").concat(m)]||p[m]||d[m]||a;return r?o.a.createElement(b,c(c({ref:t},l),{},{components:r})):o.a.createElement(b,c({ref:t},l))}));function b(e,t){var r=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var a=r.length,i=new Array(a);i[0]=m;var c={};for(var s in t)hasOwnProperty.call(t,s)&&(c[s]=t[s]);c.originalType=e,c.mdxType="string"==typeof e?e:n,i[1]=c;for(var l=2;l<a;l++)i[l]=r[l];return o.a.createElement.apply(null,i)}return o.a.createElement.apply(null,r)}m.displayName="MDXCreateElement"}}]);