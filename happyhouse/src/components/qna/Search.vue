<template>
  <div class="container">
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
      <router-link class="btn btn-primary" to="/qna">목록</router-link>
      <!-- <button v-if="!board.reply" class="btn btn-primary" @click="replyBoard()">답변</button> -->
      <button v-if="board.writer == userId" class="btn btn-warning" @click="updateBoard()">
        수정
      </button>
      <button v-if="board.writer == userId" class="btn btn-danger" @click="removeBoard()">
        삭제
      </button>
    </div>
    <div>
      <qna-reply :board="this.board" :userId="this.userId" />
    </div>
    <div class="text-right"></div>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { bus } from '@/eventbus';
import QnaReply from '@/components/qna/Reply';

export default {
  components: {
    QnaReply,
  },
  data() {
    return { board: {} };
  },
  props: ['userId'],
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  created() {
    bus.$on('searchBoard', this.searchBoard);
    this.$router.push('/qna/detail');
  },
  methods: {
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
  },
};
</script>
