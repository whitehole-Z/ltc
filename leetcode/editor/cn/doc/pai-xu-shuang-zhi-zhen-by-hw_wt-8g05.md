### 解题思路

简单排序  二分 

### 代码

```cpp
bool cmp(const string& left, const string& right) {
    if (left.size() > right.size()) {
        return true;
    }
    if (left.size() == right.size()) {
        return left < right;            
    }
    return false;
}

class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        sort(dictionary.begin(), dictionary.end(), cmp);

        for (auto str : dictionary) {
            int j = 0;
            for (int i = 0; i < s.size(); i++) {
                if (j >= str.size()) {
                    return str;
                }
                if (s[i] == str[j]) {
                    j++;
                }
            }
            if (j == str.size()) {
                return str;
            }
        }
        return "";
    }
};
```

# 二分，时间不是很理想
```cpp
bool cmp(const string& left, const string& right) {
    if (left.size() > right.size()) {
        return true;
    }
    if (left.size() == right.size()) {
        return left < right;            
    }
    return false;
}

class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        sort(dictionary.begin(), dictionary.end(), cmp);

        unordered_map<char, vector<int>> mp;
        for (int i = 0; i < s.size(); i++) {
            mp[s[i]].push_back(i);
        }
        
        for (auto str : dictionary) {
            if (s.size() < str.size()) {
                continue;
            }
            int point = -1;
            int flag = true;
            for (auto c : str) {
                if (mp.count(c) == 0) {
                    flag = false;
                    break;
                }
                int index = upper_bound(mp[c].begin(), mp[c].end(), point) - mp[c].begin();
                if (index == mp[c].size()) {
                    flag = false;
                    break;
                } else {
                    point = mp[c][index];
                }
            }
            if (flag == true) {
                return str;
            }
        }
        return "";
    }
};
```