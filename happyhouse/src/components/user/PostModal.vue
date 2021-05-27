<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <h2 class="modal-title pt-3">주소검색</h2>
            <button type="button" class="btn" @click="$emit('close')">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div ref="searchAddress" style="width: 100%; height: 450px"></div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { bus } from '@/eventbus';
export default {
  components: {
    // DaumPostcode,
  },
  data() {
    return {
      address: {
        zonecode: '',
        address1: '',
        address2: '',
      },
      showModal: false,
    };
  },
  created() {
    const script = document.createElement('script'); /* global daum */
    script.onload = () => daum.postcode.load(this.clickOpenAddressModal);
    script.src = 'http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false';
    document.head.appendChild(script);
  },
  methods: {
    clickOpenAddressModal() {
      var self = this;
      new daum.Postcode({
        oncomplete: function (data) {
          setTimeout(() => {
            bus.$emit('getAddress', data);
          }, 100);
          self.$emit('close');
        },
        width: '100%',
        height: '100%',
        maxSuggestItems: 5,
      }).embed(this.$refs.searchAddress);
    },
  },
};
</script>

<style scpoed>
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
  padding: 10px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-body {
  padding: 0 !important;
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
