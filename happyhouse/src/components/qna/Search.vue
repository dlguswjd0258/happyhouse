<template>
  <div class="container">
    <h2 class="text-center">QNA_{{ board.no }}</h2>
    <b-table-simple v-if="board">
      <tr>
        <th>번호</th>
        <td v-text="board.no"></td>
      </tr>
      <tr>
        <th>글쓴이</th>
        <td v-text="board.writer"></td>
      </tr>
      <tr>
        <th>제목</th>
        <td v-text="board.title"></td>
      </tr>
      <tr>
        <th>날짜</th>
        <td>{{ board.regtime | toDate }}</td>
      </tr>
      <tr>
        <td colspan="2" v-text="board.content"></td>
      </tr>
    </b-table-simple>
    <div class="text-right">
      <router-link class="btn btn-primary" to="/qna/moveqna/">목록</router-link>
      <button v-if="!board.reply" class="btn btn-primary" @click="replyBoard()">답변</button>
      <button class="btn btn-warning" @click="updateBoard()">수정</button>
      <button class="btn btn-danger" @click="removeBoard()">삭제</button>
    </div>
    <b-table-simple v-if="board.reply">
      <b-tr>
        <b-th>답변:</b-th>
        <b-td>{{ board.reply }}</b-td>
      </b-tr>
    </b-table-simple>
    <div class="text-right">
      <button v-if="board.reply" class="btn btn-primary" @click="replyBoard()">답변수정</button>
      <button v-if="board.reply" class="btn btn-danger" @click="removeReply()">답변삭제</button>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { bus } from '@/eventbus';

export default {
  data() {
    return { board: {} };
  },
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  created() {
    bus.$on('searchBoard', this.searchBoard);
  },
  methods: {
    replyBoard() {
      this.$router.push('/qna/reply');
      setTimeout(() => {
        bus.$emit('replyBoard', this.board);
      }, 100);
    },
    updateBoard() {
      this.$router.push('/qna/update');
      setTimeout(() => {
        bus.$emit('updateBoard', this.board);
      }, 100);
    },
    removeBoard() {
      if (confirm('정말로 삭제하시겠습니까?')) {
        axios
          .delete(`http://localhost:8090/qna/${this.board.no}`)
          .then(() => {
            alert('삭제 완료');
            this.$router.push('/qna');
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    searchBoard(no) {
      axios
        .get(`http://localhost:8090/qna/${no}`)
        .then(({ data }) => {
          this.board = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeReply() {
      axios
        .delete(`http://localhost:8090/qna/reply/${this.board.no}`)
        .then(({ data }) => {
          console.log(data);
          if (data == 'success') {
            alert('삭제 완료!');
            this.$router.push('/qna/search');
            setTimeout(() => {
              bus.$emit('searchBoard', this.board.no);
            }, 100);
          } else {
            alert('삭제중 오류 발생!');
          }
        })
        .catch((err) => {
          console.log(err);
          alert('답변 삭제중 오류 발생!');
        });
    },
  },
};
</script>
