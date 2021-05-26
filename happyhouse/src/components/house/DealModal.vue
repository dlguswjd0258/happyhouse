<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <h1 slot="header" class="modal-title">{{ deal.aptName }}</h1>

          <div class="modal-body">
            <p>이미지</p>
            <b-table-simple small>
              <colgroup>
                <col width="50%" />
                <col width="50%" />
              </colgroup>
              <b-tbody>
                <b-tr>
                  <b-td>거래금액</b-td>
                  <b-td>{{ deal.dealAmount }},000</b-td>
                </b-tr>
                <b-tr>
                  <b-td>거래연도</b-td>
                  <b-td>{{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}</b-td>
                </b-tr>
                <b-tr>
                  <b-td>건축연도</b-td>
                  <b-td>{{ deal.buildYear }}</b-td>
                </b-tr>
                <b-tr>
                  <b-td>층수</b-td>
                  <b-td>{{ deal.floor }}</b-td>
                </b-tr>
                <b-tr>
                  <b-td>크기</b-td>
                  <b-td>{{ deal.area }}</b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </div>

          <div class="modal-footer">
            <b-button variant="secondary" v-if="user.userId && wish == false" @click="addWish"
              >찜하기</b-button
            >
            <b-button variant="danger" v-if="user && wish == true" @click="deleteWish"
              >찜취소</b-button
            >
            <b-button variant="dark" @click="$emit('close')"> 닫기 </b-button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import axios from 'axios';
import { bus } from '@/dealbus';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      deal: {},
      wish: false,
    };
  },
  created() {
    console.log('생성');
    bus.$on('showDeal', this.getDeal);
  },
  methods: {
    getDeal(deal) {
      this.deal = deal;
      console.log(this.user.userId);
      if (this.user) {
        axios
          .get(`http://localhost:8090/house/wish/${this.user.userId}/${this.deal.no}`)
          .then(({ data }) => {
            if (data != 0) {
              this.wish = true;
            }
          })
          .catch(() => {
            alert('찜 조회 중 오류 발생!');
          });
      }
    },
    addWish() {
      axios
        .post(`http://localhost:8090/house/wish/${this.user.userId}/${this.deal.no}`)
        .then(() => {
          this.wish = true;
        })
        .catch(() => {
          alert('찜 등록 중 오류 발생!');
        });
    },
    deleteWish() {
      axios
        .delete(`http://localhost:8090/house/wish/${this.user.userId}/${this.deal.no}`)
        .then(() => {
          this.wish = false;
        })
        .catch(() => {
          alert('찜 삭제 중 오류 발생!');
        });
    },
  },
  computed: {
    ...mapGetters(['user']),
  },
};
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
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
