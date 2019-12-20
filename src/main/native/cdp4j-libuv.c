#include "cdp4j-libuv.h"
#include <stdlib.h>

static void alloc_buffer(uv_handle_t *handle, size_t suggested_size, uv_buf_t *buf) {
    buf->base = (char*) malloc(suggested_size);
    buf->len = (ULONG) suggested_size;
}

static void on_response(uv_stream_t* stream, ssize_t nread, const uv_buf_t* buf) {
  cdp4j_on_read_callback_java(stream->data, buf->base, (int) nread);
  free(buf->base);
}

static void on_async_write(uv_write_t* req, int status) {
  free(req);
}

static void async_write(uv_async_t* handle) {
  context_write* context = (context_write*) handle->data;
  void* thread = context->pipe->data;
  uv_write_t *request = (uv_write_t*) malloc(sizeof(uv_write_t));
  uv_buf_t buf = uv_buf_init(context->data, context->len);
  uv_write(request, (uv_stream_t*) context->pipe, &buf, 1, on_async_write);
  free(handle);
  cdp4j_on_write_callback_java(thread, context);
}

static void cdp4j_on_process_exit(uv_process_t* process, int64_t exit_status, int term_signal) {
  void* thread = process->data;
  cdp4j_on_process_exit_java(thread);
}

int cdp4j_spawn_process(uv_loop_t* loop, uv_process_t* handle, uv_process_options_t* options) {
  options->exit_cb = cdp4j_on_process_exit;
  return uv_spawn(loop, handle, options);
}

int cdp4j_start_read(uv_pipe_t* out_pipe) {
  return uv_read_start((uv_stream_t*) out_pipe, alloc_buffer, on_response);
}

int cdp4j_write_pipe(uv_loop_t* loop, uv_async_t* handle, context_write* context) {
  if (uv_async_init(loop, handle, async_write) == CDP4J_UV_SUCCESS) {
    if (uv_async_send(handle) == CDP4J_UV_SUCCESS) {
      return CDP4J_UV_SUCCESS;
    }
  }
  return CDP4J_UV_SUCCESS - 1;
}

static void on_walk(uv_handle_t *peer, void *arg) {
  if ( ! uv_is_closing(peer) ) {
    uv_close(peer, NULL);
  }
}

void cdp4j_close_loop(uv_loop_t* loop) {
  uv_walk(loop, on_walk, NULL);
  uv_run(loop, UV_RUN_DEFAULT);
}
