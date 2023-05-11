// 폴더 우클릭 > New > Other > javaScript Source File 선택
//3-3.1
//navbar_toggleBtn

const toggleBtn = document.querySelector('.navbar_toggleBtn'); // 햄버거 버튼
const menu = document.querySelector('.navbar_menu');
const icons = document.querySelector('.navbar_icons');

//addEventListener API를 통해 클릭이 될때마다 지정한 함수 호출
//햄버거 버튼이 클릭되면 menu, incons을 활성화 시킨다,

toggleBtn.addEventListener('click', () => {
	   menu.classList.toggle('active');
	   icons.classList.toggle('active');
	});

