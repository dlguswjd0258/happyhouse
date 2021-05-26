<template>
  <div class="container">
    <b-table-simple class="mt-3">
      <b-tr v-if="board.reply">
        <b-th>답변:</b-th>
        <b-td v-if="!modifyFlag">{{ board.reply }}</b-td>
        <b-td v-else>
          <textarea id="reply" ref="reply" cols="120" rows="2" v-model="board.reply"></textarea>
        </b-td>
        <b-td v-if="userId == 'admin'">
          <div v-if="!modifyFlag">
            <button class="btn btn-primary" @click="changeFlag()">수정</button>
            <button class="btn btn-danger" @click="removeReply()">삭제</button>
          </div>
          <div v-else>
            <button class="btn btn-primary" @click="replyUpdate()">완료</button>
            <button class="btn btn-danger" @click="changeFlag()">취소</button>
          </div>
        </b-td>
      </b-tr>
      <b-tr v-else-if="userId == 'admin'">
        <b-td>
          <textarea id="reply" ref="reply" cols="120" rows="2" v-model="reply"></textarea>
        </b-td>
        <b-td>
          <div>
            <button class="btn btn-primary" @click="replyInsert()">등록</button>
          </div>
        </b-td>
      </b-tr>
    </b-table-simple>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      reply: '',
      modifyFlag: false,
    };
  },
  props: ['board', 'userId'],
  methods: {
    moveHandler() {
      this.$router.push('/qna');
    },
    changeFlag() {
      this.modifyFlag = !this.modifyFlag;
    },
    replyInsert() {
      let err = true;
      let msg = '';

      if (err && (this.reply == '' || this.reply == null)) {
        msg = '답변을 입력해 주세요';
        err = false;
        this.$refs.reply.focus();
      }
      if (!err) {
        alert(msg);
      } else {
        axios
          .put('http://localhost:8090/qna/reply/', {
            no: this.board.no,
            reply: this.reply,
          })
          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              alert('답변 완료!');
              this.board.reply = this.reply;
              this.reply = '';
            } else {
              alert('답변중 오류 발생!');
            }
          })
          .catch((err) => {
            console.log(err);
            alert('답변 등록중 오류 발생!');
          });
      }
    },
    replyUpdate() {
      let err = true;
      let msg = '';

      if (err && (this.board.reply == '' || this.board.reply == null)) {
        msg = '답변을 입력해 주세요';
        err = false;
        this.$refs.reply.focus();
      }
      console.log(this.reply);
      if (!err) {
        alert(msg);
      } else {
        axios
          .put('http://localhost:8090/qna/reply/', {
            no: this.board.no,
            reply: this.board.reply,
          })
          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              alert('수정 완료!');
              this.changeFlag();
            } else {
              alert('답변중 오류 발생!');
            }
          })
          .catch((err) => {
            console.log(err);
            alert('답변 등록중 오류 발생!');
          });
      }
    },
    removeReply() {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios
          .delete(`http://localhost:8090/qna/reply/${this.board.no}`)
          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              alert('삭제 완료!');
              this.board.reply = '';
            } else {
              alert('삭제중 오류 발생!');
            }
          })
          .catch((err) => {
            console.log(err);
            alert('답변 삭제중 오류 발생!');
          });
      }
    },
  },
};
</script>
