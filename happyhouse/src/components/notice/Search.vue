<template>
  <div class="container">
    <b-table-simple v-if="notice">
      <tr>
        <th>번호</th>
        <td v-text="notice.no"></td>
      </tr>
      <tr>
        <th>글쓴이</th>
        <td v-text="notice.writer"></td>
      </tr>
      <tr>
        <th>제목</th>
        <td v-text="notice.title"></td>
      </tr>
      <tr>
        <th>날짜</th>
        <td>{{ notice.regtime | toDate }}</td>
      </tr>
      <tr>
        <td colspan="2" v-text="notice.content"></td>
      </tr>
    </b-table-simple>
    <div class="text-right">
      <router-link class="btn btn-primary" to="/notice">목록</router-link>
      <button class="btn btn-warning" @click="updateNotice()">수정</button>
      <button class="btn btn-danger" @click="removeNotice()">삭제</button>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { bus } from '@/eventbus';

export default {
  data() {
    return { notice: {} };
  },
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  created() {
    bus.$on('searchNotice', this.searchNotice);
  },
  methods: {
    updateNotice() {
      this.$router.push('/notice/update');
      setTimeout(() => {
        bus.$emit('updateNotice', this.notice);
      }, 100);
    },
    removeNotice() {
      if (confirm('정말로 삭제하시겠습니까?')) {
        axios
          .delete(`http://localhost:8090/notice/${this.notice.no}`)
          .then(() => {
            alert('삭제 완료');
            this.$router.push('/notice');
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    searchNotice(no) {
      console.log('notice : ' + no);
      axios
        .get(`http://localhost:8090/notice/${no}`)
        .then(({ data }) => {
          this.notice = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
