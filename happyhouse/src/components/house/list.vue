<template>
	<div class="row">
		<div class="col-lg-12 col-md-12 mx-auto">

			<form class="form-inline" id="frm">
				<input type='hidden' name='pageNo' id='pageNo'>
				<div class="form-group md">
					<select class="form-control" name="city" id="city">
						<option value="all">도/광역시</option>
						<option value="서울특별시">서울시</option>
						<option value="경기도">경기도</option>
						<option value="인천광역시">인천광역시</option>
						<option value="부산광역시">부산광역시</option>
						<option value="대전광역시">대전광역시</option>
						<option value="대구광역시">대구광역시</option>
						<option value="울산광역시">울산광역시</option>
						<option value="세종특별자치시">세종시</option>
						<option value="광주광역시">광주광역시</option>
						<option value="강원도">강원도</option>
						<option value="충청북도">충청북도</option>
						<option value="경상북도">경상북도</option>
						<option value="경상남도">경상남도</option>
						<option value="전라북도">전라북도</option>
						<option value="전라남도">전라남도</option>
						<option value="제주특별자치도">제주도</option>
					</select>
				</div>
				<div class="form-group md-1">
					<select class="form-control" name="dongcode" id="gu">
						<option value="all">시/구/군</option>
					</select>
				</div>
				<div class="form-group md-1">
					<select class="form-control" name="dong" id="dong">
						<option value="">동</option>
					</select>
				</div>
				<br />
				<div class="form-group md">
					<input type="text" style="display: none;" /> <input type="text"
						class="form-control" placeholder="아파트 이름" id="word" name='word'
						value='${bean.word}'>
				</div>
				<a href='#' class="btn btn-primary" id='btn'>검색</a>
			</form>

			<!-- 아파트 거래 목록 표시 -->
			<div class="row">
				<div v-if="deals.length>0">
					<b-table-simple hover small borderless>
						<colgroup>
							<col width="25%" />
							<col width="25%" />
							<col width="25%" />
							<col width="25%" />
						</colgroup>
						<b-thead>
								<b-th>아파트 이름</b-th>
								<b-th>동</b-th>
								<b-th>거래금액</b-th>
								<b-th>거래날짜</b-th>
						</b-thead>
						<b-tbody v-for="(deal,index) in deals" :key="index" @click="searchDeal(deal)">
							<b-tr >
								<b-td>{{deal.aptName}}</b-td>
								<b-td>{{deal.dong}}</b-td>
								<b-td>{{deal.dealAmount}}</b-td>
								<b-td>{{deal.dealYear}}.{{deal.dealMonth}}.{{deal.dealDay}}</b-td>
							</b-tr>
						</b-tbody>
						<b-tfoot v-if="bean">
							<b-tr>
								<b-th colspan='3' align='center'>{{bean.pageLink}}</b-th>
							</b-tr>
						</b-tfoot>
					</b-table-simple>
				</div>
				<!-- Tab panes -->
				<div class="tab-content col-6">
					<div class="tab-pane container active" id="tab1">
						<div id="map1" style="width: 100%; height: 100vh;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return { deals: [], bean:null, deal:{} };
  },
  created() {
    axios
    .get("http://localhost:8090/house/House")
    .then(({data})=>{
      this.deals = data;
    })
    .catch(()=>{
      alert("거래 정보 조회 중 오류 발생!")
    })
  },
  methods: {
	  searchDeal(deal){
		alert(deal.aptName);
        this.deal=deal;
      },
  },
};
</script>

<style>
.errorMsg {
  color: red;
  font-size: 17px;
}
</style>
