<template>
  <div>
    <div class="col-lg-8 col-md-10 mx-auto">
      <h3 class="text-center">게시글 수정</h3>
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
            <div class="text-right">
              <button class="btn btn-primary" @click="updateHandler">수정</button>
              <button class="btn btn-primary" @click="moveHandler">목록</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import { bus } from '@/eventbus';

export default {
  data() {
    return {
      no: '',
      title: '',
      writer: '',
      regtime: '',
      content: '',
    };
  },
  methods: {
    moveHandler() {
      this.$router.push('/qna');
    },
    updateHandler() {
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
          .put('http://localhost:8090/qna', {
            no: this.no,
            title: this.title,
            writer: this.writer,
            content: this.content,
          })
          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              alert('수정 완료!');
              this.$router.push('/qna/detail');
              setTimeout(() => {
                bus.$emit('searchBoard', this.no);
              }, 100);
            } else {
              alert('수정중 오류 발생!');
            }
          })
          .catch((err) => {
            console.log(err);
            alert('게시글 수정중 오류 발생!');
          });
      }
    },
    setBoard(data) {
      this.no = data.no;
      this.title = data.title;
      this.writer = data.writer;
      this.regtime = data.regtime;
      this.content = data.content;
    },
  },
  created() {
    bus.$on('updateBoard', this.setBoard);
  },
};
</script>
