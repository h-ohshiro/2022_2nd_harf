import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;


public class main {

	public static void main(String[] args) {

//		letsLambda(); //ラムダ使用例
		nullAndEnptyCheck(); // null, 空チェック
	}
	
	/**
	 * java8(ラムダ)の使い方の例
	*/
	public static void letsLambda() {
		
		// 男女のリスト生成
		final List<Person> personList = personListCreate();

		// filterでman（true）のみのリストにする
		final List<Person> manList1 = personList.stream().filter(person -> person.isMale()).collect(Collectors.toList());
		manList1.forEach((man) -> {
			System.out.println(man.getName()); // たけし, ジミー, しんご, ごろう, つよし
		});
		
		// filter内に三項演算子を入れることも可能
		final List<Person> manList2 = personList.stream().filter(person -> person.isMale() ? true : false).collect(Collectors.toList());
		manList2.forEach((man) -> {
			System.out.println(man.getName()); // たけし, ジミー, しんご, ごろう, つよし
		});
		
		// filter内に更にちょっとした処理を入れたい場合、{}で囲むことによって実現可能
		final List<Person> manList3 = personList.stream()
				.filter(person -> {if(person.isMale()) {
					return true;
					}
					return false;
				})
				.collect(Collectors.toList());
		manList3.forEach((man) -> {
			System.out.println(man.getName()); // たけし, ジミー, しんご, ごろう, つよし
		});
		
		
		// 性別をキーにMap化する
		final Map<Boolean, List<Person>> personKeyList = personList.stream().collect(Collectors.groupingBy(Person::isMale));
		personKeyList.forEach((gender, persons) -> { // genderにkeyが入っている
			if(gender) {
				persons.forEach((person) -> {
					System.out.println(person.getName()); // たけし, ジミー, しんご, ごろう, つよし
				});
			}
			if(!gender) {
				persons.forEach((person) -> {
					System.out.println(person.getName()); // あんじゅ, のりこ, えつこ
				}); 
			}
		});
		
		// mapは戻り値の型を変えることができる（<R> Stream<R> map(Function<? super T,? extends R> mapper)）
		// isMaleのbooleanをリスト化
		final List<Boolean> genderList = personList.stream().map(person -> person.isMale())
				.filter(gender -> gender.booleanValue()).collect(Collectors.toList());
		genderList.forEach((gender) -> {
			System.out.println(gender); // true, true, true, true, true（男性の数だけ返っている）
		});
		
		/* ★補足
		 * System.out.printlnの出力にList.forEachを使用しているが、List.stream().forEachでも同じ結果が得られる
		 * ただし、streamを生成する必要がなければ無駄になってしまうので、今回はList.forEachを採用している
		 * streamを活用するなら↓のようにすることで簡略化できstep数を下げれる
		 */
		personList.stream().filter(person -> person.isMale()).forEach((man) -> {
			System.out.println(man.getName()); // たけし, ジミー, しんご, ごろう, つよし
		});

	}
	
	/**
	 * null, emptyのチェック方法
	 * @return 文字列のリスト
	*/
	public static List<String> nullAndEnptyCheck() {
		
		/*
		 * List：isEmpty
		 */
		final List<String> stringList1 = new ArrayList<>();
		
		// null, 空チェックの場合、List.size() == 0を使用する方法もあるがsize()の意図や可読性を考慮するとisEmptyが良い
		if(stringList1 != null && stringList1.isEmpty()) {
			return stringList1; //空のリスト
		}
		
		/*
		 * CollectionUtils
		 * org.apache.commons.collections4.CollectionUtilsをインポートすることで使用可能
		 */
		final List<String> stringList2 = new ArrayList<>();
		
		// null,空チェックが簡単にできる
		if(CollectionUtils.isEmpty(stringList2)) {
			return stringList2; //null or 空のリスト
		}
		
		// 上記はreturnに空と分かっているリストを返しているが、コード前後を把握しないと分かりずらいことがある
		// ↓のようにCollectionsを使用することで可読性が向上し、無駄にListを生成する必要がなくなる
		if(!stringList2.equals(new ArrayList<>())) {
			return Collections.emptyList(); //空のリスト
		}
		
		/*
		 * ★補足
		 * 空リストを返す方法
		 * return stringList; 空と分かっているリストを返す←悪くはない
		 * return new ArrayList<>(); 空のリストを生成して返す←分かりやすい
		 * return Collections.emptyList(); 直列化可能な空リストを返す←これが一番いい
		 */
		
		
		/*
		 * String：isEmpty
		 */
		final String str = null;
		
		if(str.isEmpty()) { // length()が0の場合にのみtrueを返すが、そもそもstrがnullの場合はNullPointerExceptionが発生する
			//do something
		}
		if(str != null && str.isEmpty()) { // NullPointerExceptionは回避できるが...
			//do something
		}
		if(StringUtils.isEmpty(str)) { // null, 空文字チェックができる。nullの可能性を考慮するならばこちらを使用したい
			//do something
		}
		if(StringUtils.isNotEmpty(str)) { // !StringUtils.isEmpty(str)を使用するよりも可読性が向上する
			//do something
		}
		
		/*
		 * Optional型 nullかもしれない変数
		*/
		final Optional<Integer> optInt1 = Optional.empty();
		
		if(optInt1.isPresent()) { // 存在していれば
			optInt1.get().intValue(); // get()でoptionalのラップを剥がして値を取得
		}
		if(!optInt1.isPresent()) { // 存在していないのに
			optInt1.get().intValue(); // get()しようとするとNoSuchElementExceptionが発生する
		}
		
		// ofNullable()：引数がnullでない場合はその値を記述するOptionalを返し、それ以外の場合は空のOptionalを返す
		final Integer num = 2;
		final Optional<Integer> optInt2 = Optional.ofNullable(num);
		System.out.println(optInt2.get()); // 2
		
		// orElse()：optInt1が存在する場合は値を返し、それ以外の場合は引数を返す
		System.out.println(optInt1.orElse(5)); // 5
		
		return Collections.emptyList();	
	}

	/**
	 * サンプルListを作成
	 * @return personリスト
	 */
	public static List<Person> personListCreate() {
		List<Person> presidents = Arrays.asList(new Person("たけし", true, 56),
				new Person("あんじゅ", false, 61), new Person("ジミー", true, 52),
				new Person("しんご", true, 69), new Person("ごろう", true, 64),
				new Person("のりこ", false, 46), new Person("えつこ", false, 54),
				new Person("つよし", true, 47));
		return presidents;
	}
}