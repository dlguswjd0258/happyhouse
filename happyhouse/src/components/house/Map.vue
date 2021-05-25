<template>
	<div id="map" style="width:600px;height:350px;">
	</div>
</template>

<script>
import {bus} from '@/dealbus'
export default {
	data() {
		return {
			deals:[],
			markers:[],
		}
	},
	created() {
		bus.$on('showDeals',this.getDeals);
	},
	mounted() {
		
	},
	methods : {
		initMap() {
			var container = document.getElementById('map');
			var options = {
				center: new kakao.maps.LatLng(37.5642135, 127.0016985),
				level: 7
				};
			var map = new kakao.maps.Map(container, options);
			//마커추가하려면 객체를 아래와 같이 하나 만든다.
			// var marker1 = new kakao.maps.Marker({
			// 	position: new kakao.maps.LatLng(37.57, 127.01)
			// 	});
			// marker1.setMap(map);

			var imageSrc = 'https://img-premium.flaticon.com/png/512/4550/4550968.png?token=exp=1621925513~hmac=0ac36993aa79aec61cd425bf9eb4d49c', // 마커이미지의 주소입니다    
			imageSize = new kakao.maps.Size(35, 34.5), // 마커이미지의 크기입니다
			imageOption = {offset: new kakao.maps.Point(17, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
			
			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

			// 마커 추가 및 mouse over event
			this.deals.forEach(function(element){
				var marker = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(element.lat, element.lng),
				image: markerImage
				});
				marker.setMap(map);
				var iwContent = '<div style="padding:1px;">'+element.aptName+'</div>';
				var infowindow = new kakao.maps.InfoWindow({
					content : iwContent
				});
				kakao.maps.event.addListener(marker, 'mouseover', function() {
					infowindow.open(map, marker);
				});
				kakao.maps.event.addListener(marker, 'mouseout', function() {
					infowindow.close();
				});
			});
		},
		addScript() {
			const script = document.createElement('script');/* global kakao */
			script.onload = () => kakao.maps.load(this.initMap);
			script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f98eb0693d6b9ced430bdc334d33e08e';
			document.head.appendChild(script);
		},
		getDeals(deals){
			this.deals=deals;
			window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
		}
	}
}
</script>
<style >
	/* #map {
		width: 500px;
		height: 500px;
	} */
</style>
