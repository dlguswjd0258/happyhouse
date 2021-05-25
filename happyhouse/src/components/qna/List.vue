<template>
  <div class="container">
    <div class="text-right">
      <button class="btn btn-primary" @click="movePage">질문 등록</button>
    </div>
    <div v-if="qnas.length > 0">
      <b-table-simple hover small borderless>
        <colgroup>
          <col width="5%" />
          <col width="45%" />
          <col width="15%" />
          <col width="25%" />
          <col width="10%" />
        </colgroup>
        <b-thead>
          <b-th>번호</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>날짜</b-th>
        </b-thead>
        <b-tbody v-for="(qna, index) in qnas" :key="index">
          <b-tr>
            <b-td>{{ qna.no }}</b-td>
            <b-td v-text="qna.title" @click="searchBoard(qna.no)"></b-td>
            <b-td v-text="qna.writer"></b-td>
            <b-td>{{ qna.regtime | toDate }}</b-td>
            <b-td v-if="qna.reply">
              <button class="btn btn">답변완료</button>
            </b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <div v-else>게시글이 없습니다.</div>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { bus } from '@/eventbus';

export default {
  data() {
    return { qnas: [] };
  },
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  methods: {
    searchBoard(no) {
      this.$router.push('/qna/detail');
      setTimeout(() => {
        bus.$emit('searchBoard', no);
      }, 100);
    },
    movePage() {
      this.$router.push('/qna/create');
    },
  },
  created() {
    axios
      .get('http://localhost:8090/qna')
      // .then(result => {
      //   console.log(result);
      //   console.log(result.data);
      // })
      .then(({ data }) => {
        this.qnas = data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
