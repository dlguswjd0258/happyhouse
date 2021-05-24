<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
              {{deal.aptName}}
          </div>

          <div class="modal-body">
            <slot name="body">
              <p>이미지</p>
              <b-table-simple small>
						<colgroup>
							<col width="50%" />
							<col width="50%" />
						</colgroup>
						<b-tbody>
							<b-tr >
								<b-td>거래금액</b-td>
								<b-td>{{deal.dealAmount}},000</b-td>
							</b-tr>
							<b-tr >
								<b-td>거래연도</b-td>
								<b-td>{{deal.dealYear}}.{{deal.dealMonth}}.{{deal.dealDay}}</b-td>
							</b-tr>
							<b-tr >
								<b-td>건축연도</b-td>
								<b-td>{{deal.buildYear}}</b-td>
							</b-tr>
							<b-tr >
								<b-td>층수</b-td>
								<b-td>{{deal.floor}}</b-td>
							</b-tr>
							<b-tr >
								<b-td>크기</b-td>
								<b-td>{{deal.area}}</b-td>
							</b-tr>
						</b-tbody>
					</b-table-simple>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="modal-default-button">
                찜하기
              </button>
              <button class="modal-default-button" @click="$emit('close')">
                닫기
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import {bus} from '@/dealbus'

export default {
  data() {
    return {
		deal:{},
		};
	},
  created() {
    bus.$on('showDeal',this.getDeal);
  },
  methods: {
    getDeal(deal){
      this.deal=deal;
    }
  },
}
</script>


<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 500px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  /* float: right; */
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
