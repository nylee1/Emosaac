"use strict";
/*
 * ATTENTION: An "eval-source-map" devtool has been used.
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file with attached SourceMaps in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
self["webpackHotUpdate_N_E"]("pages/books/[bookId]",{

/***/ "./src/components/bookDetail/StarRating.tsx":
/*!**************************************************!*\
  !*** ./src/components/bookDetail/StarRating.tsx ***!
  \**************************************************/
/***/ (function(module, __webpack_exports__, __webpack_require__) {

eval(__webpack_require__.ts("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ \"./node_modules/react/jsx-dev-runtime.js\");\n/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ \"./node_modules/react/index.js\");\n/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var react_simple_star_rating__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-simple-star-rating */ \"./node_modules/react-simple-star-rating/dist/index.js\");\n\nvar _s = $RefreshSig$();\n\n\nconst StarRating = (param)=>{\n    let { onClick , readonly , initialValue =0  } = param;\n    _s();\n    const [rating, setRating] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(initialValue);\n    // Catch Rating value\n    const handleRating = (rate)=>{\n        setRating(rate * 2);\n        onClick(rate * 2);\n    // other logic\n    };\n    // Optinal callback functions\n    // const onPointerEnter = () => console.log('Enter')\n    // const onPointerLeave = () => console.log('Leave')\n    // const onPointerMove = (value: number, index: number) => console.log(value, index)\n    return /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n        className: \"App\",\n        children: /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(react_simple_star_rating__WEBPACK_IMPORTED_MODULE_2__.Rating, {\n            onClick: handleRating,\n            // onPointerEnter={onPointerEnter}\n            // onPointerLeave={onPointerLeave}\n            // onPointerMove={onPointerMove}\n            /* Available Props */ transition: true,\n            allowFraction: true,\n            initialValue: rating / 2,\n            allowHover: true,\n            readonly: readonly\n        }, rating, false, {\n            fileName: \"C:\\\\Users\\\\DongJu\\\\Desktop\\\\S08P22D203\\\\client\\\\src\\\\components\\\\bookDetail\\\\StarRating.tsx\",\n            lineNumber: 26,\n            columnNumber: 7\n        }, undefined)\n    }, void 0, false, {\n        fileName: \"C:\\\\Users\\\\DongJu\\\\Desktop\\\\S08P22D203\\\\client\\\\src\\\\components\\\\bookDetail\\\\StarRating.tsx\",\n        lineNumber: 25,\n        columnNumber: 5\n    }, undefined);\n};\n_s(StarRating, \"kh00J4li/JnCnh0Xl/OC09tTeT0=\");\n_c = StarRating;\n/* harmony default export */ __webpack_exports__[\"default\"] = (StarRating);\nvar _c;\n$RefreshReg$(_c, \"StarRating\");\n\n\n;\n    // Wrapped in an IIFE to avoid polluting the global scope\n    ;\n    (function () {\n        var _a, _b;\n        // Legacy CSS implementations will `eval` browser code in a Node.js context\n        // to extract CSS. For backwards compatibility, we need to check we're in a\n        // browser context before continuing.\n        if (typeof self !== 'undefined' &&\n            // AMP / No-JS mode does not inject these helpers:\n            '$RefreshHelpers$' in self) {\n            // @ts-ignore __webpack_module__ is global\n            var currentExports = module.exports;\n            // @ts-ignore __webpack_module__ is global\n            var prevExports = (_b = (_a = module.hot.data) === null || _a === void 0 ? void 0 : _a.prevExports) !== null && _b !== void 0 ? _b : null;\n            // This cannot happen in MainTemplate because the exports mismatch between\n            // templating and execution.\n            self.$RefreshHelpers$.registerExportsForReactRefresh(currentExports, module.id);\n            // A module can be accepted automatically based on its exports, e.g. when\n            // it is a Refresh Boundary.\n            if (self.$RefreshHelpers$.isReactRefreshBoundary(currentExports)) {\n                // Save the previous exports on update so we can compare the boundary\n                // signatures.\n                module.hot.dispose(function (data) {\n                    data.prevExports = currentExports;\n                });\n                // Unconditionally accept an update to this module, we'll check if it's\n                // still a Refresh Boundary later.\n                // @ts-ignore importMeta is replaced in the loader\n                module.hot.accept();\n                // This field is set when the previous version of this module was a\n                // Refresh Boundary, letting us know we need to check for invalidation or\n                // enqueue an update.\n                if (prevExports !== null) {\n                    // A boundary can become ineligible if its exports are incompatible\n                    // with the previous exports.\n                    //\n                    // For example, if you add/remove/change exports, we'll want to\n                    // re-execute the importing modules, and force those components to\n                    // re-render. Similarly, if you convert a class component to a\n                    // function, we want to invalidate the boundary.\n                    if (self.$RefreshHelpers$.shouldInvalidateReactRefreshBoundary(prevExports, currentExports)) {\n                        module.hot.invalidate();\n                    }\n                    else {\n                        self.$RefreshHelpers$.scheduleUpdate();\n                    }\n                }\n            }\n            else {\n                // Since we just executed the code for the module, it's possible that the\n                // new exports made it ineligible for being a boundary.\n                // We only care about the case when we were _previously_ a boundary,\n                // because we already accepted this update (accidental side effect).\n                var isNoLongerABoundary = prevExports !== null;\n                if (isNoLongerABoundary) {\n                    module.hot.invalidate();\n                }\n            }\n        }\n    })();\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiLi9zcmMvY29tcG9uZW50cy9ib29rRGV0YWlsL1N0YXJSYXRpbmcudHN4LmpzIiwibWFwcGluZ3MiOiI7Ozs7Ozs7O0FBQXVDO0FBQ1U7QUFRakQsTUFBTUcsYUFBYSxTQUE0RDtRQUEzRCxFQUFDQyxRQUFPLEVBQUVDLFNBQVEsRUFBRUMsY0FBZSxFQUFDLEVBQWtCOztJQUN4RSxNQUFNLENBQUNDLFFBQVFDLFVBQVUsR0FBR1AsK0NBQVFBLENBQUNLO0lBRXJDLHFCQUFxQjtJQUNyQixNQUFNRyxlQUFlLENBQUNDLE9BQWlCO1FBQ3JDRixVQUFVRSxPQUFPO1FBQ2pCTixRQUFRTSxPQUFPO0lBQ2YsY0FBYztJQUNoQjtJQUNBLDZCQUE2QjtJQUM3QixvREFBb0Q7SUFDcEQsb0RBQW9EO0lBQ3BELG9GQUFvRjtJQUVwRixxQkFDRSw4REFBQ0M7UUFBSUMsV0FBVTtrQkFDYiw0RUFBQ1YsNERBQU1BO1lBRUxFLFNBQVNLO1lBQ1Qsa0NBQWtDO1lBQ2xDLGtDQUFrQztZQUNsQyxnQ0FBZ0M7WUFDaEMsbUJBQW1CLEdBQ25CSSxZQUFZLElBQUk7WUFDaEJDLGVBQWUsSUFBSTtZQUNuQlIsY0FBY0MsU0FBUztZQUN2QlEsWUFBWSxJQUFJO1lBQ2hCVixVQUFVQTtXQVZMRTs7Ozs7Ozs7OztBQWNiO0dBL0JNSjtLQUFBQTtBQWlDTiwrREFBZUEsVUFBVUEsRUFBQSIsInNvdXJjZXMiOlsid2VicGFjazovL19OX0UvLi9zcmMvY29tcG9uZW50cy9ib29rRGV0YWlsL1N0YXJSYXRpbmcudHN4P2M0NGEiXSwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0LCB7IHVzZVN0YXRlIH0gZnJvbSAncmVhY3QnXHJcbmltcG9ydCB7IFJhdGluZyB9IGZyb20gJ3JlYWN0LXNpbXBsZS1zdGFyLXJhdGluZydcclxuXHJcbmludGVyZmFjZSBTdGFyUmF0aW5nUHJvcHMge1xyXG4gIG9uQ2xpY2s/OiBhbnk7XHJcbiAgaW5pdGlhbFZhbHVlOiBudW1iZXI7XHJcbiAgcmVhZG9ubHk6IGJvb2xlYW47XHJcbn1cclxuXHJcbmNvbnN0IFN0YXJSYXRpbmcgPSAoe29uQ2xpY2ssIHJlYWRvbmx5LCBpbml0aWFsVmFsdWUgPSAwfTogU3RhclJhdGluZ1Byb3BzKSA9PiB7XHJcbiAgY29uc3QgW3JhdGluZywgc2V0UmF0aW5nXSA9IHVzZVN0YXRlKGluaXRpYWxWYWx1ZSlcclxuXHJcbiAgLy8gQ2F0Y2ggUmF0aW5nIHZhbHVlXHJcbiAgY29uc3QgaGFuZGxlUmF0aW5nID0gKHJhdGU6IG51bWJlcikgPT4ge1xyXG4gICAgc2V0UmF0aW5nKHJhdGUgKiAyKVxyXG4gICAgb25DbGljayhyYXRlICogMilcclxuICAgIC8vIG90aGVyIGxvZ2ljXHJcbiAgfVxyXG4gIC8vIE9wdGluYWwgY2FsbGJhY2sgZnVuY3Rpb25zXHJcbiAgLy8gY29uc3Qgb25Qb2ludGVyRW50ZXIgPSAoKSA9PiBjb25zb2xlLmxvZygnRW50ZXInKVxyXG4gIC8vIGNvbnN0IG9uUG9pbnRlckxlYXZlID0gKCkgPT4gY29uc29sZS5sb2coJ0xlYXZlJylcclxuICAvLyBjb25zdCBvblBvaW50ZXJNb3ZlID0gKHZhbHVlOiBudW1iZXIsIGluZGV4OiBudW1iZXIpID0+IGNvbnNvbGUubG9nKHZhbHVlLCBpbmRleClcclxuXHJcbiAgcmV0dXJuIChcclxuICAgIDxkaXYgY2xhc3NOYW1lPSdBcHAnPlxyXG4gICAgICA8UmF0aW5nXHJcbiAgICAgICAga2V5PXtyYXRpbmd9XHJcbiAgICAgICAgb25DbGljaz17aGFuZGxlUmF0aW5nfVxyXG4gICAgICAgIC8vIG9uUG9pbnRlckVudGVyPXtvblBvaW50ZXJFbnRlcn1cclxuICAgICAgICAvLyBvblBvaW50ZXJMZWF2ZT17b25Qb2ludGVyTGVhdmV9XHJcbiAgICAgICAgLy8gb25Qb2ludGVyTW92ZT17b25Qb2ludGVyTW92ZX1cclxuICAgICAgICAvKiBBdmFpbGFibGUgUHJvcHMgKi9cclxuICAgICAgICB0cmFuc2l0aW9uPXt0cnVlfVxyXG4gICAgICAgIGFsbG93RnJhY3Rpb249e3RydWV9XHJcbiAgICAgICAgaW5pdGlhbFZhbHVlPXtyYXRpbmcgLyAyfVxyXG4gICAgICAgIGFsbG93SG92ZXI9e3RydWV9XHJcbiAgICAgICAgcmVhZG9ubHk9e3JlYWRvbmx5fVxyXG4gICAgICAvPlxyXG4gICAgPC9kaXY+XHJcbiAgKVxyXG59XHJcblxyXG5leHBvcnQgZGVmYXVsdCBTdGFyUmF0aW5nIl0sIm5hbWVzIjpbIlJlYWN0IiwidXNlU3RhdGUiLCJSYXRpbmciLCJTdGFyUmF0aW5nIiwib25DbGljayIsInJlYWRvbmx5IiwiaW5pdGlhbFZhbHVlIiwicmF0aW5nIiwic2V0UmF0aW5nIiwiaGFuZGxlUmF0aW5nIiwicmF0ZSIsImRpdiIsImNsYXNzTmFtZSIsInRyYW5zaXRpb24iLCJhbGxvd0ZyYWN0aW9uIiwiYWxsb3dIb3ZlciJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///./src/components/bookDetail/StarRating.tsx\n"));

/***/ })

});