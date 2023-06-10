<template>
  <section>
    <div id="od-header"></div>
    <div id="od-article">
      <h1 id="font-title">离群点检测系统</h1>
      <h2 id="font-title">本平台通过选择相关的离群值检测算法对数据集的数据质量进行评估</h2>
      <div>
        <div id="playground">
          <div id="playground-canvas">
            <canvas v-if="algorithmss === 0" :src="scatter" id="output" width="600" height="600"  style="border: 2px solid rgb(242, 242, 247);"></canvas>
            <el-image v-else :src="scatter" id="output" width="600" height="600"  style="border: 2px solid rgb(242, 242, 247);"></el-image>
          </div>
          <div id="data-menu">
            <el-input v-model="filename" placeholder="请选择离群点检测数据集" style="margin-bottom: 16px;"></el-input>
            <el-upload
              :on-error="upp"
              class="upload-demo"
              action="src\assets">
              <el-button size="big" type="primary">点击上传</el-button>
            </el-upload>
          </div>
          <div id="data-details">
            <el-select v-model="value" placeholder="请选择算法">
              <el-option
                v-for="item in algorithms"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>

            <div v-if="algorithmss != 0">

              <!-- lof算法 -->
              <el-row v-if="algorithmss === 2">
                <el-col :span="10">
                  <h6>第K距离</h6>
                <el-input v-model="k_nearest" placeholder="请输入第K距离"></el-input>
                </el-col>
                <el-col :span="10" style="margin-left: 5%;">
                  <h6>离群点数量</h6>
                <el-input v-model="n_outlier" placeholder="请输入离群点数量"></el-input>
                </el-col>              
              </el-row>

              <!-- dbscan算法 -->
              <el-row v-else-if="algorithmss === 4">
                <el-col :span="10">
                  <h6>Eps值</h6>
                <el-input v-model="k_nearest" placeholder="请输入Eps值"></el-input>
                </el-col>
                <el-col :span="10" style="margin-left: 5%;">
                  <h6>MinPts值</h6>
                <el-input v-model="n_outlier" placeholder="请输入MinPts值"></el-input>
                </el-col>              
              </el-row>
            </div>

            <!-- 非dbscan算法 -->
            <el-row v-else>
              <el-col :span="10">
                <h6>K近邻值</h6>
              <el-input v-model="k_nearest" placeholder="请输入K近邻值"></el-input>
              </el-col>
              <el-col :span="10" style="margin-left: 5%;">
                <h6>离群点数量</h6>
              <el-input v-model="n_outlier" placeholder="请输入离群点数量"></el-input>
              </el-col>              
            </el-row>

            <div>
              <el-button @click="alterAlgorithm" size="big" type="primary" style="margin-top: 16px; margin-bottom: 60px;">点击检测</el-button>
            </div>
            <div id="od-index">

              <!-- knn -->
              <el-row v-if="algorithmss === 1">
                <el-col :span="10">
                  <div id="index-name" style="margin-bottom: 5%;">F1 Score</div>
                  <div><div id="index-name">0.57</div></div>              
                </el-col>
                <el-col :span="10" style="margin-left: 5%;">
                  <div id="index-name" style="margin-bottom: 5%;">AUC</div>
                  <div><div id="index-name">0.97</div></div>   
                </el-col>              
              </el-row>

              <!-- lof -->
              <el-row v-else-if="algorithmss === 2">
                <el-col :span="10">
                  <div id="index-name" style="margin-bottom: 5%;">F1 Score</div>
                  <div><div id="index-name">0.36</div></div>              
                </el-col>
                <el-col :span="10" style="margin-left: 5%;">
                  <div id="index-name" style="margin-bottom: 5%;">AUC</div>
                  <div><div id="index-name">0.94</div></div>   
                </el-col>              
              </el-row>
              
              <!-- hilout -->
              <el-row v-else-if="algorithmss === 3">
                <el-col :span="10">
                  <div id="index-name" style="margin-bottom: 5%;">F1 Score</div>
                  <div><div id="index-name">0.52</div></div>              
                </el-col>
                <el-col :span="10" style="margin-left: 5%;">
                  <div id="index-name" style="margin-bottom: 5%;">AUC</div>
                  <div><div id="index-name">0.96</div></div>   
                </el-col>              
              </el-row>
              
              <!-- dbscan -->
              <el-row  v-else-if="algorithmss === 4">
                <el-col :span="10">
                  <div id="index-name" style="margin-bottom: 5%;">F1 Score</div>
                  <div><div id="index-name">0.68</div></div>              
                </el-col>
              </el-row>

            </div>
            <div id="od-index2"></div>            
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
    data() {
      return {
        algorithms: [{
          value: 1,
          label: 'KNN算法'
        }, {
          value: 2,
          label: 'LOF算法'
        }, {
          value: 3,
          label: 'HilOut算法'
        }, {
          value: 4,
          label: 'DBSCAN算法'
        }, {
          value: 5,
          label: 'ABOD算法'
        }],
        value: '',
        filename: '',
        filename1: 'D://Project/DataSet/MAT/vowels.mat', 
        scatter: '',
        scatter1: 'knn_vowels_scatter.png',
        scatter2: 'lof_vowels_scatter.png',
        scatter3: 'hilout_vowels_scatter.png',
        scatter4: 'dbscan_vowels_scatter.png',
        k_nearest: '',
        n_outlier: '',
        param: 0,
        algorithmss: 0
      }
    },
    methods: {
      alterAlgorithm() {
        setTimeout(() => {

          this.algorithmss = this.value
          if(this.algorithmss === 1){
            this.scatter = this.scatter1
          }
          else if(this.algorithmss === 2){
            this.scatter = this.scatter2
          }
          else if(this.algorithmss === 3){
            this.scatter = this.scatter3
          }
          else{
            this.scatter = this.scatter4
          }
          
        }, 3000);
      },
      upp() {
        this.filename = this.filename1
      }
    }
  }
</script>

<style>
body {
  display: block;
  margin: 0px;
}

h1 {
  margin-top: 60px;
  font-size: 56px;
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

h2 {
  margin-top: 12px;
  font-size: 24px;
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

h6 {
  margin-top: 12px;
  margin-bottom: 8px;
  font-size: 12px;
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

.el-button {
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

.el-input {
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

.el-select {
  font-family: HoeflerText-Regular, Cochin, Georgia, serif;
}

#od-header {
  display: block;
  position: relative;
  height: 70px;
  background: hsl(210, 2%, 21%);
  width: 100%;
  box-sizing: border-box;
  z-index: 2;
  color: rgba(0, 0, 0, 0.8);
  border-bottom: 1px solid rgba(0, 0, 0, 0.08);
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.05);
}

#od-article {
  display: block;
  color: rgba(0, 0, 0, 0.8);
  font: 17px/1.55em -apple-system, BlinkMacSystemFont, "Roboto", sans-serif;
  padding-bottom: 72px;
  background: white;
}

#od-index {
  font-size: 12px;
  line-height: 18px;
  display: block;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  color: rgba(0, 0, 0, 0.5);
  padding-top: 12px;
  padding-bottom: 12px;
}

#od-index2 {
  line-height: 36px;
  display: block;
  /* border-top: 1px solid rgba(0, 0, 0, 0.1); */
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  color: rgba(0, 0, 0, 0.5);
  padding-top: 46px;
  padding-bottom: 36px;
}

#font-title {
  width: 816px;
  margin-left: calc(40% - 988px / 2);
  margin-right: auto;
}

#playground {
  overflow: hidden;
  font-family: 'Open Sans', sans-serif;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
  margin-top: 36px;
  margin-left: calc(40% - 988px / 2);
  margin-right: auto;
  padding: 36px 0 0 0;
  z-index: 1000;
}

#playground-canvas {
    float: left;
    width: 40%;
    margin-right: 4%;
}

#data-menu {
    width: 35%;
    float: left;
    margin-bottom: 24px;
    overflow: hidden;
}

#data-details {
    width: 35%;
    float: left;
}

#index-name {
  display: block;
  text-align: left;
  line-height: 18px;
  font: 15px/1.55em -apple-system, BlinkMacSystemFont, "Roboto", sans-serif;
}

</style>