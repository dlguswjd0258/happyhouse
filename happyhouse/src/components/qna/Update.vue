<template>
  <div class="container">
    <h2 class="text-center">게시글 수정</h2>
    <table class="table table-condensed w-25">
      <tr>
        <th>제목</th>
        <td>
          <input
            type="text"
            id="title"
            ref="title"
            placeholder="제목을 입력해주세요"
            v-model="title"
          />
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea id="content" ref="content" cols="30" rows="10" v-model="content"></textarea>
        </td>
      </tr>
    </table>
    <div class="text-right">
      <button class="btn btn-primary" @click="updateHandler">수정</button>
      <button class="btn btn-primary" @click="moveHandler">목록</button>
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
      this.$router.push('/qna/moveqna/');
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
