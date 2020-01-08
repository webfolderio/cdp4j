/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
#include <stdlib.h>
#include <string.h>
#include "uv.h"

#ifndef ULONG
  #define ULONG unsigned long
#endif

#define CDP4J_UV_SUCCESS 0

typedef union uv_data_s {
  uv_stream_t* stream;
  int          fd;
} uv_data_t;

typedef struct context_write {
  uv_pipe_t* pipe;
  char*      data;
  int        len;
} context_write;

typedef struct context_close {
  uv_loop_t* loop;
  uv_pipe_t* in_pipe;
  uv_pipe_t* out_pipe;
} context_close;

int cdp4j_spawn_process(uv_loop_t*            loop,
                        uv_process_t*         handle,
                        uv_process_options_t* options);

int cdp4j_write_pipe(uv_loop_t*     loop,
                     context_write* context);

int cdp4j_start_read(uv_pipe_t* out_pipe);

void cdp4j_async_close_loop(uv_loop_t* loop,
                            uv_pipe_t* in_pipe,
                            uv_pipe_t* out_pipe);

int cdp4j_write_async(uv_loop_t* loop, void* thread);

void cdp4j_on_read_callback_java(void* thread, char* data, int let);

void cdp4j_on_process_exit_java(void* thread);

int cdp4j_on_async_write_callback_java(void* thread);
