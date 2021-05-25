<template>
  <div>
    <h3 class="text-center">게시글 등록</h3>
    <table class="table table-condensed w-25">
      <tr>
        <th>제목</th>
        <td>
          <input
            type="text"
            id="title"
            v-model="title"
            ref="title"
            placeholder="제목을 입력해주세요"
          />
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea id="content" v-model="content" ref="content" cols="30" rows="10"></textarea>
        </td>
      </tr>
    </table>
    <div class="text-right">
      <button class="btn btn-primary" @click="createHandler">등록</button>
      <button class="btn btn-primary" @click="moveHandler">목록</button>
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
      this.$router.push('/qna/moveqna/');
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
          .post('http://localhost:8090/qna', {
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
