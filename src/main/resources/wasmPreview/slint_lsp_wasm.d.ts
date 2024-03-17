/* tslint:disable */
/* eslint-disable */
/**
* Register DOM event handlers on all instance and set up the event loop for that.
* You can call this function only once. It will throw an exception but that is safe
* to ignore.
*/
export function run_event_loop(): void;
/**
* @param {any} init_param
* @param {Function} send_notification
* @param {SendRequestFunction} send_request
* @param {ImportCallbackFunction} load_file
* @returns {SlintServer}
*/
export function create(init_param: any, send_notification: Function, send_request: SendRequestFunction, load_file: ImportCallbackFunction): SlintServer;

export type ResourceUrlMapperFunction = (url: string) => Promise<string | undefined>;
export type SignalLspFunction = (data: any) => void;



type ImportCallbackFunction = (url: string) => Promise<string>;
type SendRequestFunction = (method: string, r: any) => Promise<any>;
type HighlightInPreviewFunction = (file: string, offset: number) => void;


/**
*/
export class PreviewConnector {
  free(): void;
/**
* @param {SignalLspFunction} lsp_notifier
* @param {ResourceUrlMapperFunction} resource_url_mapper
* @param {string} style
* @param {boolean} experimental
* @returns {Promise<PreviewConnector>}
*/
  static create(lsp_notifier: SignalLspFunction, resource_url_mapper: ResourceUrlMapperFunction, style: string, experimental: boolean): Promise<PreviewConnector>;
/**
* @returns {any}
*/
  current_style(): any;
/**
* @returns {Promise<any>}
*/
  show_ui(): Promise<any>;
/**
* @param {any} value
*/
  process_lsp_to_preview_message(value: any): void;
}
/**
*/
export class SlintServer {
  free(): void;
/**
* @param {any} value
* @returns {Promise<void>}
*/
  process_preview_to_lsp_message(value: any): Promise<void>;
/**
* @param {any} cap
* @returns {any}
*/
  server_initialize_result(cap: any): any;
/**
* @param {string} content
* @param {any} uri
* @param {number} version
* @returns {Promise<any>}
*/
  reload_document(content: string, uri: any, version: number): Promise<any>;
/**
* @param {any} _id
* @param {string} method
* @param {any} params
* @returns {Promise<any>}
*/
  handle_request(_id: any, method: string, params: any): Promise<any>;
/**
* @returns {Promise<void>}
*/
  reload_config(): Promise<void>;
}

export type InitInput = RequestInfo | URL | Response | BufferSource | WebAssembly.Module;

export interface InitOutput {
  readonly memory: WebAssembly.Memory;
  readonly slint_mock_elapsed_time: (a: number) => void;
  readonly slint_get_mocked_time: () => number;
  readonly slint_send_mouse_click: (a: number, b: number, c: number) => void;
  readonly slint_send_keyboard_char: (a: number, b: number, c: number) => void;
  readonly send_keyboard_string_sequence: (a: number, b: number) => void;
  readonly run_event_loop: (a: number) => void;
  readonly __wbg_previewconnector_free: (a: number) => void;
  readonly previewconnector_create: (a: number, b: number, c: number, d: number, e: number, f: number) => void;
  readonly previewconnector_current_style: (a: number) => number;
  readonly previewconnector_show_ui: (a: number, b: number) => void;
  readonly previewconnector_process_lsp_to_preview_message: (a: number, b: number, c: number) => void;
  readonly __wbg_slintserver_free: (a: number) => void;
  readonly create: (a: number, b: number, c: number, d: number, e: number) => void;
  readonly slintserver_process_preview_to_lsp_message: (a: number, b: number) => number;
  readonly slintserver_server_initialize_result: (a: number, b: number, c: number) => void;
  readonly slintserver_reload_document: (a: number, b: number, c: number, d: number, e: number) => number;
  readonly slintserver_handle_request: (a: number, b: number, c: number, d: number, e: number) => number;
  readonly slintserver_reload_config: (a: number) => number;
  readonly __wbindgen_malloc: (a: number, b: number) => number;
  readonly __wbindgen_realloc: (a: number, b: number, c: number, d: number) => number;
  readonly __wbindgen_export_2: WebAssembly.Table;
  readonly wasm_bindgen__convert__closures__invoke1_mut__h0803eefb7ecbc5f2: (a: number, b: number, c: number) => void;
  readonly wasm_bindgen__convert__closures__invoke0_mut__h3dcef5cc69de8702: (a: number, b: number) => void;
  readonly wasm_bindgen__convert__closures__invoke2_mut__h41a87842d4be8cb9: (a: number, b: number, c: number, d: number) => void;
  readonly wasm_bindgen__convert__closures__invoke1__h2e71c1b7aca74692: (a: number, b: number, c: number) => void;
  readonly __wbindgen_free: (a: number, b: number, c: number) => void;
  readonly __wbindgen_exn_store: (a: number) => void;
  readonly __wbindgen_add_to_stack_pointer: (a: number) => number;
}

export type SyncInitInput = BufferSource | WebAssembly.Module;
/**
* Instantiates the given `module`, which can either be bytes or
* a precompiled `WebAssembly.Module`.
*
* @param {SyncInitInput} module
*
* @returns {InitOutput}
*/
export function initSync(module: SyncInitInput): InitOutput;

/**
* If `module_or_path` is {RequestInfo} or {URL}, makes a request and
* for everything else, calls `WebAssembly.instantiate` directly.
*
* @param {InitInput | Promise<InitInput>} module_or_path
*
* @returns {Promise<InitOutput>}
*/
export default function __wbg_init (module_or_path?: InitInput | Promise<InitInput>): Promise<InitOutput>;
