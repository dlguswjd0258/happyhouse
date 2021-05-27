<template>
  <b-container>
    <div>
      <h1 class="text-center mb-5">관심 지역</h1>
    </div>
    <div class="form-inline mb-3">
      <div class="form-group md mr-3">
        <b-form-select v-model="city">
          <b-form-select-option value="null">도/광역시</b-form-select-option>
          <b-form-select-option value="서울특별시">서울시</b-form-select-option>
          <b-form-select-option value="경기도">경기도</b-form-select-option>
          <b-form-select-option value="인천광역시">인천광역시</b-form-select-option>
          <b-form-select-option value="부산광역시">부산광역시</b-form-select-option>
          <b-form-select-option value="대전광역시">대전광역시</b-form-select-option>
          <b-form-select-option value="대구광역시">대구광역시</b-form-select-option>
          <b-form-select-option value="울산광역시">울산광역시</b-form-select-option>
          <b-form-select-option value="세종특별자치시">세종시</b-form-select-option>
          <b-form-select-option value="광주광역시">광주광역시</b-form-select-option>
          <b-form-select-option value="강원도">강원도</b-form-select-option>
          <b-form-select-option value="충청북도">충청북도</b-form-select-option>
          <b-form-select-option value="경상북도">경상북도</b-form-select-option>
          <b-form-select-option value="경상남도">경상남도</b-form-select-option>
          <b-form-select-option value="전라북도">전라북도</b-form-select-option>
          <b-form-select-option value="전라남도">전라남도</b-form-select-option>
          <b-form-select-option value="제주특별자치도">제주도</b-form-select-option>
        </b-form-select>
      </div>
      <div class="form-group md-1 mr-3">
        <b-form-select v-model="gu">
          <b-form-select-option :value="null">시/구/군</b-form-select-option>
          <b-form-select-option
            v-bind="gus"
            v-for="(gu, index) in gus"
            :key="index"
            :value="gu.dongcode"
            >{{ gu.gugun }}</b-form-select-option
          >
        </b-form-select>
      </div>
      <div class="form-group md-1 mr-3">
        <b-form-select v-model="dong">
          <b-form-select-option value="null">동</b-form-select-option>
          <b-form-select-option
            v-bind="dongs"
            v-for="(dong, index) in dongs"
            :key="index"
            :value="dong.dong"
            >{{ dong.dong }}</b-form-select-option
          >
        </b-form-select>
      </div>
      <br />
      <div class="form-group md mr-3">
        <input type="text" style="display: none" />
        <input type="text" class="form-control" placeholder="아파트 이름" v-model="word" />
      </div>
      <a href="#" class="btn btn-primary" @click="search">검색</a>
    </div>
    <!-- 아파트 거래 목록 표시 -->
    <div v-if="wishes.length > 0">
      <el-table class="table-responsive table" :data="wishes" header-row-class-name="thead-light">
        <el-table-column label="아파트명" min-width="140px" prop="aptName"> </el-table-column>
        <el-table-column label="동" min-width="60px" prop="dong"> </el-table-column>
        <el-table-column label="거래 금액" min-width="90px" prop="dealAmount"> </el-table-column>
        <el-table-column label="거래 날짜" min-width="90px" prop="dealDate">
          <template v-slot="{ row }">
            {{ row.dealYear }}.{{ row.dealMonth }}.{{ row.dealDay }}
          </template>
        </el-table-column>
        <el-table-column label="평수" min-width="90px" prop="area"> </el-table-column>
        <el-table-column label="층수" min-width="90px" prop="floor"> </el-table-column>
        <el-table-column label="건축연도" min-width="90px" prop="buildYear"> </el-table-column>
      </el-table>
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

    <div v-else>관심 목록이 없습니다.</div>
  </b-container>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
import { Table, TableColumn } from 'element-ui';

export default {
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      wishes: [],
      city: null,
      gus: [],
      gu: [],
      dongs: [],
      dong: null,
      word: null,
      pageNo: 1,
      total: 0,
      perPage: 0,
    };
  },
  created() {
    this.searchWish();
  },
  methods: {
    searchWish() {
      if (this.word == '') this.word = null;
      if (this.dong == '') this.dong = null;
      axios
        .get(
          `http://localhost:8090/house/wish/${this.pageNo}/${this.dong}/${this.word}/${this.user.userId}`
        )
        .then(({ data }) => {
          this.wishes = data.wishes;
          this.total = data.bean.total;
          this.perPage = data.bean.perPage;
        })
        .catch((err) => {
          console.log(err);
          alert('거래 정보 조회 중 오류 발생!');
        });
    },
    search() {
      this.pageNo = 1;
      this.searchWish();
      this.getTotal();
    },
    searchGu() {
      axios
        .get(`http://localhost:8090/house/GuSearch/${this.city}`)
        .then(({ data }) => {
          this.gus = data;
        })
        .catch(() => {
          alert('구 정보 조회 중 오류 발생!');
        });
    },
    searchDong() {
      axios
        .get(`http://localhost:8090/house/DongSearch/${this.gu}`)
        .then(({ data }) => {
          this.dongs = data;
        })
        .catch(() => {
          alert('동 정보 조회 중 오류 발생!');
        });
    },
  },
  watch: {
    city: 'searchGu',
    gu: 'searchDong',
    pageNo: 'searchWish',
  },
  computed: {
    ...mapGetters(['user']),
  },
};
</script>

<style>
.errorMsg {
  color: red;
  font-size: 17px;
}
</style>
