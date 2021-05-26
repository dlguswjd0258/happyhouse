<template>
  <div class="container">
    <div class="text-right" v-if="userId == 'admin'">
      <button class="btn btn-primary" @click="movePage">등록</button>
    </div>
    <div v-if="notices.length > 0">
      <b-table-simple class="table-responsive table">
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
        <b-tbody v-for="(notice, index) in notices" :key="index">
          <b-tr>
            <b-td>{{ notice.no }}</b-td>
            <b-td v-text="notice.title" @click="searchNotice(notice.no)"></b-td>
            <b-td v-text="notice.writer"></b-td>
            <b-td>{{ notice.regtime | toDate }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
      <div class="overflow-auto mt-3">
        <b-pagination
          align="center"
          v-model="pageNo"
          :total-rows="total"
          :per-page="interval"
          aria-controls="my-table"
        ></b-pagination>
      </div>
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
    return {
      notices: [],
      pageNo: 1,
      total: 0,
      interval: 0,
    };
  },
  props: ['userId'],
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  watch: {
    pageNo: 'getList',
  },
  methods: {
    searchNotice(no) {
      this.$router.push('/notice/detail');
      setTimeout(() => {
        bus.$emit('searchNotice', no);
      }, 100);
    },
    movePage() {
      this.$router.push('/notice/create');
    },
    getList() {
      axios
        .get(`http://localhost:8090/notice/${this.pageNo}/all/${null}`)
        .then(({ data }) => {
          this.notices = data.notices;
          this.total = data.bean.total;
          this.interval = data.bean.interval;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.getList();
  },
};
</script>
