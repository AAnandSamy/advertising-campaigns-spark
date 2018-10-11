# advertising-campaigns-spark

### Advertising Data science Use Case

Data science suspects that advertising campaigns are showing the same ad to users too many
times (a high frequency) as they browse their favorite websites. They’ve asked the data
engineers to investigate.

Given steaming datasets contains ad events ,find all of the users that saw the same ad more than 5x on a site

###### Actual events:

Campaign_ID |   GUID |   Timestamp |   Ad_ID |   Site_ID |   Site_URL |   Ad_Type |   Tag_Type |   Placement_ID |   Wild_Card |   Custom_1 |   Custom_2 |   Custom_3 |   Custom_4 |   Custom_5 |   Custom_6 |   Custom_7 |   Custom_8 |   Custom_9 |   Custom_10 |   Pass |   Opt_Out |   Iframe |   Weight |   Size |   Tactic |   Visible |   Exposure_Time |   Pre_Ad_Exposure |   Post_Ad_Exposure |   Campaign_Imp_Count |   Referrer |   User_Agent |   City |   State |   Country |   Zip_Code |   Language |   IP_Address |   guidsource |   Ad_Server_Cd
--- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   --- |   ---
|67231      |683059ce-ead1-4aed-92db-b264b8239fb9|1508866680304|test2-relevancy-video-fitness|aap        |-                |1      |-       |-           |men25-54 |-       |-       |-       |-       |-       |-       |-       |-       |-       |-        |-   |0      |-     |-     |-                        |-         |-      |-            |1              |1               |3                 |-       |-         |rotherham            |rot  |gbr    |s65 1aa |en-GB   |86f389089f8cb6f58aa87561bcf7bec9d700c40b|1         |3           |
|71071      |9fa98f98-3c0c-4d80-bc55-7341fae50c75|1508866680371|standardvideo                |clearstream|-                |1      |-       |-           |-        |-       |-       |-       |-       |-       |-       |-       |-       |-       |-        |-   |0      |-     |-     |-                        |-         |-      |-            |1              |1               |14                |-       |-         |baldock              |hrt  |gbr    |sg7 5aa |en-GB   |86f389089f8cb6f58aa87561bcf7bec9d700c40b|1         |3           |
|67231      |057775a5-1c0b-47f6-a463-0955c97c9707|1508866682121|test3-relevancy-video-fitness|aap        |-                |1      |-       |-           |men25-54 |-       |-       |-       |-       |-       |-       |-       |-       |-       |-        |-   |0      |-     |-     |-                        |-         |-      |-            |1              |1               |22                |-       |-         |trowbridge           |wil  |gbr    |ba14 0aa|pl-PL   |86f389089f8cb6f58aa87561bcf7bec9d700c40b|1         |3           |
|74508      |28a9a74f-4ecc-4103-9d7c-444047b8c48f|1508866682193|video30_1                    |amnetgroup |-                |1      |-       |-           |shows    |-       |-       |-       |-       |-       |-       |-       |-       |-       |-        |-   |0      |-     |-     |-                        |-         |-      |-            |1              |1               |1                 |-       |-         |campos dos goytacazes|rj   |bra    |0       |pt-BR   |d579a50a5a6c8bf4c820f9e29caeb68337977e70|2         |3           |
|73272      |2fa6df5f-8034-4ec6-bdcb-de95b992aa26|1508866682471|-                            |aol        |ir.ebaystatic.com|0      |6       |-           |-        |-       |-       |-       |-       |-       |-       |-       |-       |-       |-        |1   |0      |1     |5941  |wn-ed-52-en-o---0-0-0-0-0|2053954664|3      |-            |0              |0               |0                 |-       |-         |cardiff              |crf  |gbr    |cf14 0gp|en-GB   |86f389089f8cb6f58aa87561bcf7bec9d700c40b|1         |3           |


Each events represents one user’s view of an ad on a site
- GUID is a unique identifier for a user
- Filter out any ad events that do not have a valid GUID (i.e. GUID is “unsupported”, “-”,
etc)
- Output should be Ad ID, Site ID, Frequency and Total users that saw the ad at that
frequency. Frequency is defined as the total number of times the same ad was shown to
a user on the same site.
- The output should be sorted in descending order by frequency.

###### Suspects events:

Ad_ID | Site_ID | Frequency | TotalUser
--- | --- | --- | ---
|standarddisplay|adcolony|25911936 |11952    |
|logo           |realtor |1937501  |59       |
|logo           |realtor |1649252  |44       |
|logo           |realtor |1481982  |46       |
|logo           |realtor |1380435  |35       |

We are able to find out the suspected ads
- Meaning 11952 users saw 'standarddisplay' 25911936 times on adcolony.
- 59 users saw 'logo' 1937501 times on realtor.