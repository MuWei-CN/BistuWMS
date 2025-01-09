import {reactive} from "vue";

export let tobe_resolve = [];
export function set_tobe_resolve(js){
    tobe_resolve = js;
}
export const tobe_resolve_num = reactive({
    num:0,
    setNum(n){this.num = n},
    increase(){this.num ++},
    decrease(){this.num --}
});