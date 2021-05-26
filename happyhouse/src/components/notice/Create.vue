<template>
  <div>
    <div class="col-lg-8 col-md-10 mx-auto">
      <h3 class="text-center">게시글 등록</h3>
      <!-- Tab panes -->
      <div class="tab-content">
        <div class="tab-pane container active pt-5" id="tab1">
          <form class="form-horizontal" id="memberform" method="post" action="">
            <div class="form-group" align="left">
              <label for="title">제목</label>
              <input
                type="text"
                class="form-control mr-3"
                id="title"
                v-model="title"
                ref="title"
                placeholder="제목을 입력해주세요"
              />
            </div>
            <div class="form-group" align="left">
              <label for="content">내용</label>
              <div class="form-inline">
                <textarea
                  id="content"
                  class="form-control mr-3"
                  v-model="content"
                  ref="content"
                  cols="100%"
                  rows="10"
                ></textarea>
              </div>
            </div>
          </form>
          <div class="form-group text-right">
            <button class="btn btn-primary" @click="createHandler">등록</button>
            <button class="btn btn-primary" @click="moveHandler">목록</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      title: '',
      content: '',
    };
  },
  methods: {
    moveHandler() {
      this.$router.push('/notice');
    },
    createHandler() {
      let err = true;
      let msg = '';

      if (err && this.title == '') {
        msg = '제목을 입력해 주세요';
        err = false;
        this.$refs.title.focus();
      }
      if (err && this.content == '') {
        msg = '내용을 입력해 주세요';
        err = false;
        this.$refs.content.focus();
      }

      if (!err) {
        alert(msg);
      } else {
        axios
          .post('http://localhost:8090/notice', {
            title: this.title,
            content: this.content,
          })
          .then(({ data }) => {
            console.log(data);
            msg = '등록 처리시 문제가 발생했습니다.';
            if (data == 'success') {
              alert('등록이 완료 되었습니다.');
              this.moveHandler();
            } else {
              alert('등록 실패!');
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>
