# 万年暦 Android版

Android StudioをPCにインストールせず、GitHub ActionsでAPKを生成するプロジェクトです。

- 既存の万年暦の暦計算JavaScriptを維持し、Android WebViewに同梱します。
- `.github/workflows/build-android.yml` がクラウドでDebug APKをビルドします。
- ビルド成果はActionsの実行ページで `mannenreki-android-debug` としてダウンロードできます。
- Android実機での動作は別途検証が必要です。

※開発中のDebug APKです。正式配布・ストア公開用の署名済み版ではありません。
