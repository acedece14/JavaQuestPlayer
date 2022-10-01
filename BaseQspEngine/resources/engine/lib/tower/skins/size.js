

onresize = function()
{
	// �������� ������� ������ ����
    var htmlWidth  = document.getElementsByTagName('html')[0].clientWidth;
    var htmlHeight = document.getElementsByTagName('html')[0].clientHeight;
	
	// �������� ������� ���������������
	var scalable = document.getElementsByTagName('body')[0];
	
	// ������ ����������� �������� ���� (�� ��� ������� ���������� ���������������)
	var defWidth = 1280;
	var defHeight = 720;
	
	// ������������ �������
	zoom = Math.min(htmlWidth / defWidth, htmlHeight / defHeight);
	scalable.style.zoom = zoom;
}

document.addEventListener("DOMContentLoaded", onresize);
window.addEventListener('resize', onresize, true); 

// ���������� �����
var skinSplashScreenVisible = true;


function qspSkinOnSetGroupedContent() {
	// ��� ������ ������ ���������� ����,
	// ����� �����-�����.
	if (skinSplashScreenVisible) {
		skinHideSplashScreen();
	}
}

// ����� �����-�����.
function skinHideSplashScreen() {
	skinSplashScreenVisible = false;
	$("#skin-ui-wrapper").show();
	$("#skin-splashscreen-foreground").fadeOut('slow');
}