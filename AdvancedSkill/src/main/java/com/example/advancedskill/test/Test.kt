package com.example.advancedskill.test

/**
 * @Author: driverSkr
 * @Time: 2024/1/5 13:56
 * @Description: $
 */

fun findMissingNumber(arr: IntArray): Int? {
    // 检查数组是否为空或者长度为1（这两种情况无法确定缺失值）
    if (arr.isEmpty() || arr.size == 1) return null
    val result1 = arr.let {
        arr[3]
        arr[0]
    }
    val result2 = with(arr){
        arr[3]
        arr[1]
    }
    arr.withIndex()

    println("let = $result1, with = $result2")

    // 获取数组的最小值和最大值
    val minValue = arr.minOrNull()!!
    val maxValue = arr.maxOrNull()!!

    // 如果最大值小于最小值加数组长度减一，则说明存在缺失元素
    if (maxValue > minValue + arr.size - 1) {
        // 循环遍历从最小值到最大值+1的范围，找出未出现在数组中的数
        for (num in minValue..(maxValue + 1)) {
            if (!arr.contains(num)) {
                return num
            }
        }
    } else { // 如果没有缺失元素，在最大值后增加一位更大的数并返回
        return maxValue + 1
    }

    // 应该不会执行到这里，除非输入的数据有误
    return null
}

fun main() {
    val array = intArrayOf(6, 3, 4, 5, 1)
    val missingNumber = findMissingNumber(array)
    println("缺失的元素是：$missingNumber") // 输出：2
}