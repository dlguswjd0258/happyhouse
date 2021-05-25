<template>
	<div class="row">
		<div class="col-lg-12 col-md-12 mx-auto">
			<b-container class="bv-example-row">
			<form class="form-inline" id="frm">
				<input type='hidden' name='pageNo' id='pageNo'>
				<div class="form-group md">
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
				<div class="form-group md-1">
					<b-form-select  v-model="gu">
						<b-form-select-option :value="null" >시/구/군</b-form-select-option>
						<b-form-select-option v-bind="gus" v-for="(gu,index) in gus" :key="index" :value="gu.dongcode">{{gu.gugun}}</b-form-select-option>
					</b-form-select>
				</div>
				<div class="form-group md-1">
					<b-form-select v-model="dong">
						<b-form-select-option value="null">동</b-form-select-option>
						<b-form-select-option v-bind="dongs" v-for="(dong,index) in dongs" :key="index" :value="dong.dong">{{dong.dong}}</b-form-select-option>

					</b-form-select>
				</div>
				<br />
				<div class="form-group md">
					<input type="text" style="display: none;" /> <input type="text"
						class="form-control" placeholder="아파트 이름" v-model="word"
						>
				</div>
				<a href='#' class="btn btn-primary" @click="search">검색</a>
			</form>
			</b-container>
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
		<Map></Map>
			</div>
		</div>
		<Modal @close="closeModal" v-if="showModal">
		</Modal>
	</div>
</template>

<script>
import axios from 'axios';
import Modal from '@/components/house/DealModal'
import Map from '@/components/house/Map'
import {bus} from '@/dealbus'
import { mapGetters } from 'vuex';

export default {
	components:{
		Modal,
		Map
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
    	this.getTotal();
	},
	methods: {
	searchDeal(){
		if(this.word=='')
			this.word=null;
		if(this.dong=='')
			this.dong=null;
		axios
		.get(`http://localhost:8090/house/${this.currentPage}/${this.dong}/${this.word}`)
		.then(({data})=>{
		this.deals = data;
		bus.$emit('showDeals',data);
		})
		.catch(()=>{
		alert("거래 정보 조회 중 오류 발생!")
		})

	},
	getTotal(){
		axios
		.get(`http://localhost:8090/house/total/${this.currentPage}/${this.dong}/${this.word}`)
		.then(({data})=>{
			this.total = data;
		})
		.catch(()=>{
			alert("전체 조회 중 오류 발생!")
		})
	},
	search(){
		this.currentPage=1;
		this.searchDeal();
		this.getTotal();
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
