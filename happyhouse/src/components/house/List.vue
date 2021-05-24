<template>
	<div class="row">
		<div class="col-lg-12 col-md-12 mx-auto">

			<form class="form-inline" id="frm">
				<input type='hidden' name='pageNo' id='pageNo'>
				<div class="form-group md">
					<select class="form-control" v-model="city">
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
					<select class="form-control" v-model="gu">
						<option value="all">시/구/군</option>
						<option v-bind="gus" v-for="(gu,index) in gus" :key="index" :value="gu.dongcode">{{gu.gugun}}</option>
					</select>
				</div>
				<div class="form-group md-1">
					<select class="form-control" v-model="dong">
						<option value="">동</option>
						<option v-bind="dongs" v-for="(dong,index) in dongs" :key="index" :value="dong.dong">{{dong.dong}}</option>

					</select>
				</div>
				<br />
				<div class="form-group md">
					<input type="text" style="display: none;" /> <input type="text"
						class="form-control" placeholder="아파트 이름" v-model="word"
						>
				</div>
				<a href='#' class="btn btn-primary" @click="searchDeal">검색</a>
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
						<b-tbody v-model="deals" v-for="(deal,index) in deals" :key="index" @click="openModal(deal)">
							<b-tr >
								<b-td>{{deal.aptName}}</b-td>
								<b-td>{{deal.dong}}</b-td>
								<b-td>{{deal.dealAmount}}</b-td>
								<b-td>{{deal.dealYear}}.{{deal.dealMonth}}.{{deal.dealDay}}</b-td>
							</b-tr>
						</b-tbody>
						<b-tfoot>
							<div class="overflow-auto">
								<b-pagination
								v-model="currentPage"
								:total-rows="total"
								:per-page="perPage"
								aria-controls="my-table"
								></b-pagination>
							</div>
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
		<Modal @close="closeModal" v-if="showModal">
		</Modal>
	</div>
</template>

<script>
import axios from 'axios';
import Modal from '@/components/house/DealModal'
import {bus} from '@/dealbus'
export default {
	components:{
		Modal,
	},
    data() {
    return {
		deals: [],
		deal:{},
		showModal:false,
		city:null,
		gus:[],
		gu:[],
		dongs:[],
		dong:null,
		word:null,
		perPage: 10,
        currentPage: 1,
		total:0,
		};
	},	
	created() {
    	this.searchDeal();
	},
	methods: {
	searchDeal(){
		axios
		.get(`http://localhost:8090/house/${this.currentPage}/${this.dong}/${this.word}`)
		.then(({data})=>{
		this.deals = data;
		this.deals.sort();
		})
		.catch(()=>{
		alert("거래 정보 조회 중 오류 발생!")
		})
		axios
		.get(`http://localhost:8090/house/total/${this.currentPage}/${this.dong}/${this.word}`)
		.then(({data})=>{
			this.total = data;
		})
		.catch(()=>{
			alert("전체 조회 중 오류 발생!")
		})
		console.log(this.deals);
	},
	searchGu(){
		axios
    	.get(`http://localhost:8090/house/GuSearch/${this.city}`)
    	.then(({data})=>{
			this.gus = data;
    	})
    	.catch(()=>{
      		alert("구 정보 조회 중 오류 발생!")
    	})
		
	},
	searchDong(){
		axios
    	.get(`http://localhost:8090/house/DongSearch/${this.gu}`)
    	.then(({data})=>{
			
      		this.dongs = data;
    	})
    	.catch(()=>{
      		alert("동 정보 조회 중 오류 발생!")
    	})
	},
	openModal(deal){
		setTimeout(()=>{
        	bus.$emit('showDeal',deal);
        },100);
		this.showModal=true;
    },
	closeModal(){
		this.showModal=false;
	},
	},
	watch:{
		city: 'searchGu',
		gu: 'searchDong',
		currentPage: 'searchDeal'
	},
};
</script>

<style>
.errorMsg {
  color: red;
  font-size: 17px;
}
</style>
