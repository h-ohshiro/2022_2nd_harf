window.onload = function(){
	
	$(document).on('click','.button ', function (){
		/*
		  * Ajax処理のサンプル
		*/
		$.ajax(
			{
				type: "POST",
				url: "/checkWarning",
				dataType: "json", // text, html, xml, json, jsonp, scriptから指定可能。省略の場合はjQueryから自動検出する
				data: { name: "John", location: "Boston" }
			}
		).done(function( msg ) {
			alert( "チェック結果: " + msg );
		});
	});
	    /*
	    * ちなみに不具合調査や機能改修時においてAjax周りを確認する際、chromeの場合、開発者モードのNetworkタブで通信状態を確認できる
	  　* 下記のようにボタンを1回押した際に1回だけAjaxでPOSTしている場合は分かりやすくて良いが、
	　  * 複数回POSTしている場合にその全体像を把握する際の一助になる
		* （条件分岐でPOSTするしないのケースがあるので頼りすぎるのも危ない）
		*/
		
		/*
		js(jQuery)で覚えたこととかを以下に記載するつもりでしたが、$()やgetElementByIdやparentやchildrenとかで要素引っ張てきて加工したり
		ぐらいのものなので、jsをコーディングする際によく使用しているchromeの開発者モードで普段使用している機能等を記述します。
		
		●Consoleタブ
		　　・consoleに関数名を記述するとその内容が確認できる。
			・デバッグで停止させた状態でconsoleに変数を記述するとその内容が見える。（SourcesタグのScopeでも確認可能）
			 また、変数の中身を変えることも可能。（element = "test"　等）
		　　・ある要素の親の中にある特定のclassを持っている要素を引っ張ってきたりでややこしい場合、eclipseでコーディングして実際に動かして訂正してまた動かして...
		　　　とするよりも、consoleに記述することでその場で記述内容の正否が判断できるので効率が良い。
		
		●Sourcesタブ
		　　・ctrl + P で指定したファイルを開く。（eclipseの、ctrl + sheft ＋ R に相当）
		　　・ctrl + G で指定した行へジャンプする。（eclipseの、ctrl + L に相当）
		　　・行を左クリックするとブレークポイントとなる。また、右クリックすることで条件付きブレークポイントを選択できる。
			・ブレークポイントで止めた状態でScopeにある変数の中身を変更することができる。
			　（FireFoxは現在その機能がなくなったため、Consoleに記述することで変更可能）
		　　・Wacthに変数を登録するとその時の変数の状態が常時表示される。
		　　・ctrl + shift + F ですべてのファイルに対して検索をかけれる。（ctrl + F でそのファイル内で検索）
		
		●NetWorkタブ
		　　・開発者モードを開いた状態で通信するとその状態を確認できる。
		　　・通信の名前をクリックするとリクエストのHeadersやResponseを確認できる。
		　　・StatusCodeも確認できるので、どの通信でエラーが返ってきているかすぐに分かり、そのエンドポイントでControllerが特定しやすい。
		　　・丸斜線アイコン（〇に＼）をクリックすると表示をクリアできる。
		*/
}